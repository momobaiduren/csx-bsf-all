package com.yh.csx.bsf.demo.rocketmq;


import com.yh.csx.bsf.core.serialize.JsonSerializer;
import com.yh.csx.bsf.shardingjdbc.base.MasterOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yh.csx.bsf.mq.rocketmq.RocketMQConsumerProvider;
import com.yh.csx.bsf.mq.rocketmq.RocketMQProducerProvider;

@SpringBootApplication
@RestController //implements WebMvcConfigurer
public class ProducerApplication  {

    @Autowired(required = false)
    private RocketMQProducerProvider rocketMQProducerProvider;
//    @Autowired(required = false)
//    private RocketMQConsumerProvider rocketMQConsumerProvider;

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);
        RocketMQConsumerProvider consumerProvider = context.getBean(RocketMQConsumerProvider.class);
        consumerProvider.subscribe("csx-bsf-demo-test-consumer-01","csx-bsf-demo-test",new String[]{"aaaa","ddd"},(msg)->{
            System.out.println(new JsonSerializer().serialize( msg));
        },String.class);
    }
    int i=0;
    @GetMapping("/sendMessage")
    public void sendMessage() {
        i=i+1;
        rocketMQProducerProvider.sendMessage("csx-bsf-demo-test","aaaa","测试"+ i);

    }

}