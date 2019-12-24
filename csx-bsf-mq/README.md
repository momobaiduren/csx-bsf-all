# MQ集成

## 介绍
本系统集成了消息队列Rocket MQ.本系统由于同一封装及优化MQ客户端，简化业务端的使用。

关于更详细RocketMQ的搭建及使用，详见[官网](http://rocketmq.apache.org/)

## 依赖引用

```java 
<dependency>
	<artifactId>csx-bsf-mq</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>
```

## 配置说明

```
#mq
bsf.mq.enabled = false
bsf.rocketmq.namesrvaddr = 127.252.193.20:9876;127.252.193.18:9876
```



## 示例代码
```
    @Autowired(required = false)
    private RocketMQProducerProvider rocketMQProducerProvider;
    @Autowired(required = false)
    private RocketMQConsumerProvider rocketMQConsumerProvider;

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);
        
        //订阅消息
        RocketMQConsumerProvider consumerProvider = context.getBean(RocketMQConsumerProvider.class);
        consumerProvider.subscribe("csx-bsf-demo-test-consumer-01","csx-bsf-demo-test",(msg)->{
            System.out.println(msg);
        },String.class);
        
        //发送消息
        rocketMQProducerProvider.sendMessage("csx-bsf-demo-test","测试"+ DateUtils.formatTime(new Date()));
    }
    
 ```