# Eureka &KONG集成

## 介绍

1. Eureka是Netflix开发的服务发现框架，本身是一个基于REST的服务，主要用于定位运行在Spirng Cloud域中的中间层服务，实现SpringCloud的服务发现功能，以达到负载均衡和中间层服务故障转移的目的。本系统用于包装配置优化过的注册。

2. [Kong](https://docs.konghq.com/)是一个基于Apache License 2.0的开源项目，是一个云原生的快速可扩的分布式微服务抽象层，应用场景为微服务的API网关。
   Kong是一个在Nginx中运行的Lua应用程序，可以通过lua-nginx模块实现，Kong不是用这个模块编译Nginx，而是与OpenRestry一起发布，OpenRestry已经包含了lua-nginx-module，OpenRestry是Nginx的一组扩展功能模块。
   
   
3. 在本系统中，优化Eureka 客户端配置，实现KONG路由的自动注册与发现。


## 依赖引用

```java 
<dependency>
	<artifactId>csx-bsf-eureka</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>
```

## 配置说明

```shell
#Eureka 启用配置
bsf.eureka.client.enabled = true
#Eureka 服务地址
eureka.client.serviceUrl.defaultZone = 
#Erueka 心跳
eureka.client.registry-fetch-interval-seconds = 5
eureka.instance.lease-expiration-duration-in-seconds = 5
eureka.instance.lease-renewal-interval-in-seconds = 2
eureka.instance.prefer-ip-address = true


#KONG 自动注册配置
#KONG 启用开关
bsf.eureka.kong.enabled = true		
#KONG的IP地址	
bsf.eureka.kong.ip = 	
#KONG的服务端口					
bsf.eureka.kong.port = 8001		
#KONG的监看检查策略		
bsf.eureka.kong.healthchecks.active.unhealthy.tcp_failures = 1	
#KONG健康检查超时间
bsf.eureka.kong.healthchecks.active.unhealthy.timeouts = 1
#KONG健康检失败{}次数，后任务不可用
bsf.eureka.kong.healthchecks.active.unhealthy.http_failures = 1
#KONG健康检探活时间间隔
bsf.eureka.kong.healthchecks.active.unhealthy.interval = 5
#KONG健康检，成功{}次数后，认为成功
bsf.eureka.kong.healthchecks.active.healthy.successes = 1
#KONG健康检时间间隔
bsf.eureka.kong.healthchecks.active.healthy.interval = 5
```