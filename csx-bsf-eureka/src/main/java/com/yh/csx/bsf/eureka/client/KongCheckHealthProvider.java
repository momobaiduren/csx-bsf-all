package com.yh.csx.bsf.eureka.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yh.csx.bsf.core.base.BsfException;
import com.yh.csx.bsf.core.http.DefaultHttpClient;
import com.yh.csx.bsf.core.http.DynamicObj;
import com.yh.csx.bsf.core.http.HttpClient;
import com.yh.csx.bsf.core.http.HttpException;
import com.yh.csx.bsf.core.system.ProcessExitEvent;
import com.yh.csx.bsf.core.thread.ThreadPool;
import com.yh.csx.bsf.core.util.*;
import lombok.Data;
import lombok.val;

import org.apache.http.entity.ContentType;
import org.springframework.boot.ApplicationArguments;

import java.util.List;

/**
 * @author: chejiangyi
 * @version: 2019-09-24 16:48
 * eureka服务同步kong负载均衡和健康检查配置信息
 **/
public class KongCheckHealthProvider implements org.springframework.boot.ApplicationRunner {
    private static String template="http://{ip}:{port}/upstreams";
    private String target;
    KongProperties kongProperties;
    public KongCheckHealthProvider(KongProperties kongProperties){
        this.kongProperties = kongProperties;
        //JVM 停止或重启时，注销kong
        ProcessExitEvent.register(()->{
            try{
                down();
            }
            catch(Exception e){ LogUtils.error(ThreadPool.class, EurekaProperties.Project,"注销kong时出错",e); }
        },0,false);
    }
    protected String getTemplate(){
        return template.replace("{ip}",kongProperties.getKongIp())
                .replace("{port}",kongProperties.getKongPort()+"");
    }

    @Override
    public void run(ApplicationArguments var1)  {
        up();
    }
    public void up(){
        try {
             target = getTarget();
             val data = new DynamicObj( DefaultHttpClient.Default.get(getTemplate() + "/", HttpClient.Params.custom()
                     .add("name", getUpstreamName())
                     .build()));

             val params = HttpClient.Params.custom()
                     .add("name",getUpstreamName())
                     .add("healthchecks.active.http_path",StringUtils.isEmpty(kongProperties.getHealthChecksHttpPath())?kongProperties.getServerServletContextPath()+"/bsf/health/ping/":kongProperties.getHealthChecksHttpPath())
                     .add("healthchecks.active.unhealthy.tcp_failures",kongProperties.getHealthChecksUnhealthyTcpFailures())
                     .add("healthchecks.active.unhealthy.timeouts",kongProperties.getHealthChecksUnhealthyTimeouts())
                     .add("healthchecks.active.unhealthy.http_failures",kongProperties.getHealthChecksUnhealthyHttpFailures())
                     .add("healthchecks.active.unhealthy.interval",kongProperties.getHealthChecksUnhealthyInterval())
                     .add("healthchecks.active.healthy.successes",kongProperties.getHealthChecksHealthySuccesses())
                     .add("healthchecks.active.healthy.interval",kongProperties.getHealthChecksHealthyInterval());
            if(data!=null&&data.parse("data.@0.id",Object.class)!=null){
                //params.add("id",data.parse("data.@0.id",String.class));
                DefaultHttpClient.Default.endStream(DefaultHttpClient.Default.patch(getTemplate() + "/"+getUpstreamName(),
                        params.build(), response -> {
                            int code = response.getStatusLine().getStatusCode();
                            if (code != 201 && code != 200 && code != 409) {
                                throw new HttpException("更新操作失败");
                            }
                            return response;
                        }));
            }
            else {
                DefaultHttpClient.Default.endStream(DefaultHttpClient.Default.post(getTemplate() + "/",
                        params.build(), response -> {
                            int code = response.getStatusLine().getStatusCode();
                            if (code != 201 && code != 200 && code != 409) {
                                throw new HttpException("添加操作失败");
                            }
                            return response;
                        }));
            }

            String target = getTarget();
            Target item = getItem(target);
            if (item == null) {
                DefaultHttpClient.Default.endStream(DefaultHttpClient.Default.post(getTemplate() + "/" + getUpstreamName() + "/targets", HttpClient.Params.custom().add("target", target).setContentType(ContentType.APPLICATION_FORM_URLENCODED).build(),response -> {
                    if(response.getStatusLine().getStatusCode()!=201){
                        throw new HttpException("添加操作失败");
                    }
                    return response;
                }));

            }
            LogUtils.info(KongCheckHealthProvider.class,EurekaProperties.Project,"Kong自动注册均衡节点成功");
        }
        catch (Exception exp){
            throw new BsfException("Kong自动注册均衡节点异常",exp);
        }
    }

    private String getUpstreamName(){
        return kongProperties.getSpringApplicationName()+"-upstream-auto";
    }

    public void down(){
        try {
            Target item = getItem(target);
            if (item != null) {
                DefaultHttpClient.Default.endStream(DefaultHttpClient.Default.delete(getTemplate() + "/"+getUpstreamName()+"/targets/" + item.id,null,response -> {
                    if(response.getStatusLine().getStatusCode()!=204){
                        throw new HttpException("delete操作失败");
                    }
                    return response;
                }));
            }
            LogUtils.info(KongCheckHealthProvider.class,EurekaProperties.Project,"Kong自动卸载均衡节点成功");
        }
        catch (Exception exp){
            throw new BsfException("Kong自动卸载均衡节点异常",exp);
        }
    }

    protected String getTarget(){
        return getIP()+":"+getPort();
    }

    protected String getIP(){      
    	return NetworkUtils.getIpAddress();
    }

    protected int getPort(){
        return ContextUtils.getConfigurableWebServerApplicationContext().getWebServer().getPort();
    }

    protected Target getItem(String target){
        Targets targets = DefaultHttpClient.Default.get(getTemplate()+"/"+getUpstreamName()+"/targets",new TypeReference<Targets>() {});
       for(Target t:targets.data){
           if(target.equalsIgnoreCase(t.target))
           {
               return t;
           }
       }
       return null;
    }

    @Data
    private static class Targets{
        private List<Target> data;
        private int total;
    }
    @Data
    private static class Target{
        private long created_at;
        private String id;
        private String upstream_id;
        private String target;
        private int weight;
    }

}
