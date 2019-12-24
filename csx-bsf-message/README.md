# 消息集成说明
## 介绍
用于短信、邮件、钉钉消息功能集成
## 依赖引用

```java 
<dependency>
	<artifactId>csx-bsf-message</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>
```

## 配置说明
```shell
# 消息功能启用
bsf.message.enabled=true
# 钉钉消息功能启用
bsf.message.dingding.enabled=true
# 钉钉发送消息，需要token。项目根据不同需要，设置不同的token
bsf.health.warn.dingding.system.access_token=

# 短信模块，启用
# 短信模块基于HTTP请求发送，可依据具体短信服务扩展
bsf.message.sms.enabled=false
sms.url=
sms.url.bak=
sms.user=
sms.password=
sms.server=
```

## 示例代码
短信示例

```java

    @Autowired
    private SmsProvider smsProvider;   
    /**
    *	发送短信
    */
    public void sendMessage(String phone){
    		smsProvider.sendText(phone, "CRM测试短信，收到请忽略！","CRM");    	
    }
```


钉钉消息示例


```java
	@Autowired
 	DingdingProvider dingdingProvider;
 	/**
    *	发送短信
    */
    public void sendMessage(String phone){
    		dingdingProvider.sendText(phone, "CRM测试短信，收到请忽略！","CRM");    	
    }
```




