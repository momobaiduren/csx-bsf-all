package com.yh.csx.bsf.demo;

import com.google.common.collect.Lists;
import com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient;
import com.yh.csx.bsf.core.base.BsfEnvironmentEnum;
import com.yh.csx.bsf.core.common.Collector;
import com.yh.csx.bsf.core.thread.ThreadPool;
import com.yh.csx.bsf.core.util.ContextUtils;
import com.yh.csx.bsf.core.util.ThreadUtils;
import com.yh.csx.bsf.core.util.WebUtils;
import com.yh.csx.bsf.demo.job.HelloJob;
import com.yh.csx.bsf.message.dingding.DingdingProvider;
import com.yh.csx.bsf.message.sms.SmsProvider;
import com.yh.csx.bsf.mq.rocketmq.RocketMQProducerProvider;
import com.yh.csx.bsf.redis.RedisProvider;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Slf4j
@RestController
@SpringBootApplication
public class DemoApplication //implements WebMvcConfigurer
         {
    @Autowired(required = false)
    SmsProvider smsProvider;

     @Autowired(required = false)
     DingdingProvider dingdingProvider;

    @GetMapping("/hello")
    public Object hello(String name) {
        return new Date();
    }

     @GetMapping("/hello2")
     public Object hello2(String name) {
       try{ Thread.sleep(60*1000);}catch (Exception e){}
        return "60s ok";
     }

    @GetMapping("/sms")
    public void sendSms(int type, String content) throws Exception {
        if (type == 1) {
            smsProvider.sendVoiceCode("17757717657", content);
        } else {
            smsProvider.sendText("17757717657", content);
        }
    }
     @GetMapping("/dingding")
     public void sendDingding(String content) throws Exception {
         dingdingProvider.sendText(new String[]{},"测试",content);

     }

    @Bean
    public HelloJob helloJob(){
        return new HelloJob();
    }

    @GetMapping("/logger")
    public String logger(String msg){
        log.info(msg);
        return "success";
    }




    public static void main(String[] args) {
        System.out.println("aaadfadf");
        val context = SpringApplication.run(DemoApplication.class, args);
        System.out.println("dfasdfasdlfjlajdsflOKOK");
        try{ Thread.sleep(30000);}catch (Exception e){}
        System.exit(0);

//
//        val start0 = system.currentTimeMillis();
//        List<Integer> as = new ArrayList<>();
//        for(int i=1000;i>0;i--){
//            as.add(i);
//        }
//        system.out.println("for耗时:"+(system.currentTimeMillis()-start0));
//        //预热
//        ThreadPool.system.parallelFor2("测试",100,as,c->{
//            try{ Thread.sleep(c%30);}catch (Exception e){}
//        });
//        //try{ Thread.sleep(5000);}catch (Exception e){}
//
//
//        //算法2
//        List<Integer> result2 = Collections.synchronizedList(new ArrayList<>());
//        val start2 = system.currentTimeMillis();
//        ThreadPool.system.parallelFor2("测试2",10,as,c->{
//            //try{ Thread.sleep(c%30);}catch (Exception e){}
//            result2.add(c);
////            if(c%5==0||c==60){
////                throw new RuntimeException("aaa"+c);
////            }
//        });
//
//        system.out.println("算法2耗时:"+(system.currentTimeMillis()-start2));
//        val resuts2 = result2.toArray();
//        Arrays.sort(resuts2);
//        Arrays.stream(resuts2).forEach(c->{
//            system.out.println("算法2结果:"+c);
//        });

//        //算法1
//        List<Integer> result1 = Collections.synchronizedList(new ArrayList<>());
//        val start = system.currentTimeMillis();
//        ThreadPool.system.parallelFor("测试1",10,as,c->{
//            //try{ Thread.sleep(c%30);}catch (Exception e){}
//            result1.add(c);
//        });
//
//        system.out.println("算法1耗时:"+(system.currentTimeMillis()-start));
//        val resuts1 = result1.toArray();
//        Arrays.sort(resuts1);
//        Arrays.stream(resuts1).forEach(c->{
//            system.out.println("算法1结果:"+c);
//        });


//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//        system.out.println( new Date().toString());
//        system.out.println( new Date(new Date().toString()).toString());
       // SpringApplication.run(DemoApplication.class, args);
       //val item =  ContextUtils.getApplicationContext().getBean(HandlerExecutionChain.class);
       // WebUtils.getRequest().getServletContext().
        //InitialContext..doLookup("java:comp/env/jdbc");
        //val ds = ContextUtils.getApplicationContext().getBean("dataSource");
//        val a =((TomcatWebServer)((AnnotationConfigServletWebServerApplicationContext) ContextUtils.getApplicationContext()).getWebServer()).getTomcat().getServer().getUtilityExecutor();
//        ScheduledThreadPoolExecutor c=null;
        var b= 1;
//        for( int i=0;i<10;i++) {
//            val b=i;
//            new Thread(() -> {
//                while (true) {
//                    try {
//                        Thread.sleep(5000 * b);
//                    } catch (Exception ex) {
//                    }
//                    throw new RuntimeException("aaa"+b);
//                }
//            }).start();
//        }
    }

}
