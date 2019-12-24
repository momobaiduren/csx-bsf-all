# xxl-job集成说明

## 介绍
[XXL-JOB](https://github.com/xuxueli/xxl-job)是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。

本模块用于集成XXL-JOB,更方便业务使用。

## 依赖引用
添加bsf模块到项目依赖中

```java 
<dependency>
	<artifactId>csx-bsf-job</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>
```


## 配置说明

```shell
## XXL-JOB 集成配置
#job开关,默认为true，非必填
bsf.job.enabled=false 
#xxljob地址，非必填，默认根据bsf.env环境自动设置地址
xxl.job.admin.addresses=   
#（执行器名称，非必填，默认等于spring.application.name）
xxl.job.executor.appname=csx-bsf-demo  
#（执行器ip，非必填，默认本机ip，如果存在多网卡，则必填，否在可能会导致调度中心无法连接到此执行器）
xxl.job.executor.ip=    
#（执行器端口，非必填，默认9999，如果单机部署多个执行器，则分别指定）
xxl.job.executor.port=9999   
# （非必填，如果调度中心配置，此处需要配置）       
xxl.job.accessToken=            
#（非必填，任务日志目录，默认job-logs/） 
xxl.job.executor.logpath=          
#（非必填，默认为7，任务日志保存天数）
xxl.job.executor.logretentiondays=   
```
更详细的配置请[xxl-job官网](https://github.com/xuxueli/xxl-job)

## 代码示例

创建任务
1. 任务需要继承IJobHandler，通过注解@JobHandler来指定任务名称（调动中心配置时使用）

```java
@JobHandler("helloJob")
public class HelloJob extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("hello job run with: " + param);
        return ReturnT.SUCCESS;
    }
}
```
2. 通过@Component或@Bean的方式来创建任务的Bean

```java
@Bean
public HelloJob helloJob(){
    return new HelloJob();
}
```