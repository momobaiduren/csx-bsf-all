package com.yh.csx.bsf.eureka.client;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.yh.csx.bsf.core.common.Collector;
import com.yh.csx.bsf.core.util.ClassPoolUtils;
import com.yh.csx.bsf.core.util.ContextUtils;
import com.yh.csx.bsf.core.util.LogUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import lombok.val;
import org.apache.http.client.methods.Configurable;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;

/**
 * @author: chejiangyi
 * @version: 2019-08-01 16:37
 **/
public class EurekaMonitor {
    private static String newMethodCode() {
        String code = "{" +
                "       return ($r)com.yh.csx.bsf.core.common.Collector.Default.hook(\"bsf.eureka.client.hook\").run($1.url(),$0,\"executeOld\",$args);\n" +
                "}";
        return code;
    }

    private static boolean isload = false;

    public static void init() {
        try {
            ClassPool classPool = ClassPoolUtils.getInstance();
            CtClass ctClass = classPool.get("org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient");
            if (!isload) {
                isload = true;
                CtMethod ctMethod = ctClass.getDeclaredMethod("execute");
                CtMethod mold = CtNewMethod.copy(ctMethod, "executeOld", ctClass, null);
                ctClass.addMethod(mold);
                ctMethod.setBody(newMethodCode());

                if (ctClass.isFrozen()) {
                    ctClass.defrost();
                }
                ctClass.toClass();
                LogUtils.info(EurekaMonitor.class, EurekaProperties.Project, "注入eureka execute ok");
            }
        } catch (Exception exp) {
            LogUtils.error(EurekaMonitor.class,  EurekaProperties.Project, "注入eureka execute 异常", exp);
        }
    }

    public void collect() {
        val instance = ApplicationInfoManager.getInstance();
        if(instance!=null&&instance.getInfo()!=null&&instance.getInfo().getStatus()!=null){
            Collector.Default.value("bsf.eureka.instance.status").set(instance.getInfo().getStatus().name());
        }else{
            Collector.Default.value("bsf.eureka.instance.status").set(InstanceInfo.InstanceStatus.UNKNOWN.name());
        }
        val rabbonAutoConfiguration = ContextUtils.getApplicationContext().getBean(RibbonAutoConfiguration.class);
        if (rabbonAutoConfiguration != null) {
            val springFactory = rabbonAutoConfiguration.springClientFactory();
            int allLeased = 0;
            int allAvailable = 0;
            int allMax = 0;
            int allPending = 0;
            StringBuilder sb = new StringBuilder();
            for (String contextName : springFactory.getContextNames()) {
                val manager = springFactory.getInstance(contextName, PoolingHttpClientConnectionManager.class);
                allLeased += manager.getTotalStats().getLeased();
                sb.append(String.format("%s:租借连接数%s\r\n", contextName, manager.getTotalStats().getLeased()));
                allAvailable += manager.getTotalStats().getAvailable();
                sb.append(String.format("%s:有效连接数%s\r\n", contextName, manager.getTotalStats().getAvailable()));
                allMax += manager.getTotalStats().getMax();
                sb.append(String.format("%s:最大连接数%s\r\n", contextName, manager.getTotalStats().getMax()));
                allPending += manager.getTotalStats().getPending();
                sb.append(String.format("%s:等待连接数%s\r\n", contextName, manager.getTotalStats().getPending()));

                val clients = springFactory.getInstances(contextName,CloseableHttpClient.class);
                for(val kv: clients.entrySet())
                {
                    val client =kv.getValue();
                    if(client  instanceof Configurable) {
                        val config = ((Configurable) client).getConfig();
                        val requestTimeOut = config.getConnectionRequestTimeout();
                        sb.append(String.format("%s:请求超时时间%s\r\n",kv.getKey(), requestTimeOut));
                        val connectTimeOut = config.getConnectTimeout();
                        sb.append(String.format("%s:连接超时时间%s\r\n",kv.getKey(), connectTimeOut));
                        val socketTimeOut = config.getSocketTimeout();
                        sb.append(String.format("%s:socket超时时间%s\r\n",kv.getKey(), socketTimeOut));
                    }
                }
            }
            Collector.Default.value("bsf.eureka.client.pool.leased").set(allLeased);
            Collector.Default.value("bsf.eureka.client.pool.available").set(allAvailable);
            Collector.Default.value("bsf.eureka.client.pool.max").set(allMax);
            Collector.Default.value("bsf.eureka.client.pool.pending").set(allPending);
            Collector.Default.value("bsf.eureka.client.pool.detail").set(sb.toString());
        }
    }

}
