# Cat集成说明

## 介绍
CAT(Central Application Tracking)基于Java开发的实时监控平台，主要包括移动端监控，应用侧监控，核心网络层监控，系统层监控等。

CAT是一个提供实时监控报警，应用性能分析诊断的工具。[更多详情](https://github.com/dianping/cat)。

本系统用于集成CAT并统一封装监控埋点，对业务无侵入实现监控。
## 添加依赖
```java
<dependency>
	<artifactId>csx-bsf-cat</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>	
```
## 配置说明
```
#启用cat
bsf.cat.enabled=true
#Cat服务器地址，必填
cat.server.url=
#Cat的domain取值spring.application.name，必填
spring.application.name=
#default,cross
#cross模式为调用链模式
bsf.cat.filter=default
```
当配置有org.apache.ibatis.plugin.Interceptor，org.mybatis.spring.transaction.SpringManagedTransaction类时，系统自动拦截sql和事务。