# csx-bsf-all
## 介绍
BSF 为 base service framework 的简写，定义为永辉彩食鲜技术团队的基础框架,用于基础服务的集成和跟业务无关的基础技术集成。

BSF集成了自研的监控报警，用来监控各个服务系统的性能及异常告警。集成并封装Apollo,Rocket MQ,Redis, Elastic Search,ELK,XXLJOB, Sharding JDBC,Cat,Eureka,七牛云等第三方中间件，提供简易使用的底层框架。

## 愿景
为了更好地支持业务开发，让开发人员从中间件中解放出来，专注业务以提高开发效率。同时基础框架集中统一优化中间件相关服务及使用，为开发人员提供高性能,更方便的基础服务接口及工具。

## 项目结构规范说明
```
csx-bsf-all 
    -- csx-bsf-core (项目核心类库)
    -- csx-bsf-demo (项目集成使用demo)
    -- csx-bsf-dependencies (项目依赖pom定义)
        -- README.md (说明文档，必须有)
    -- csx-bsf-starter （项目full-start包）
    -- csx-bsf-elk (ELK集成)
    -- csx-bsf-job (XXL-JOB集成)
    -- csx-bsf-cat (CAT监控集成)
    -- csx-bsf-apollo (Apollo配置中心集成)
    -- csx-bsf-message (消息-短信-钉钉消息集成)
    -- csx-bsf-shardingjdbc (分库分表ShardingJDBC 集成) 
    -- csx-bsf-mq (消息队列Rocket MQ集成) 
    -- csx-bsf-redis(缓存Redis集成)
    -- csx-bsf-eureka(服务注册与发现集成)
    -- csx-bsf-file（文件服务集成）
    -- csx-bsf-elasticsearch(ES集成) 
    -- csx-bsf-health（自研健康检查） 
    -- 框架名 (例如:csx-bsf-elk,cat,apollo等)
```

## 相关文档
本系统个子模块分别集成分装了对应中间件服务，文档如下：
1. [csx-bsf-core](csx-bsf-core/README.md)
2. [csx-bsf-demo](csx-bsf-demo/README.md)
3. [csx-bsf-dependencies](csx-bsf-dependencies/README.md)
4. [csx-bsf-starter](csx-bsf-starter/README.md)
5. [csx-bsf-elk](csx-bsf-elk/README.md) 
6. [csx-bsf-job](csx-bsf-job/README.md) 
7. [csx-bsf-cat](csx-bsf-cat/README.md) 
8. [csx-bsf-apollo](csx-bsf-apollo/README.md) 
9. [csx-bsf-message](csx-bsf-message/README.md) 
10. [csx-bsf-shardingjdbc](csx-bsf-shardingjdbc/README.md) 
11. [csx-bsf-mq](csx-bsf-mq/README.md) 
12. [csx-bsf-redis](csx-bsf-redis/README.md) 
13. [csx-bsf-eureka](csx-bsf-eureka/README.md) 
14. [csx-bsf-file](csx-bsf-file/README.md) 
15. [csx-bsf-elasticsearch](csx-bsf-elasticsearch/README.md) 
16. [csx-bsf-health](csx-bsf-health/README.md) 

## 编译说明
1. mvn install csx-bsf-dependencies
2. mvn install csx-bsf-all

## 版本升级/切换
```
备注: 格式:1.0.0-RELEASE (版本号+-+RELEASE/SNAPSHOT) 
cd csx-bsf-dependencies
mvn versions:set -DgenerateBackupPoms=false
或
mvn versions:set -DgenerateBackupPoms=false -DnewVersion={version}
```
    
## 使用说明

1. 依赖引用

    继承csx-bsf-dependencies
``` 
    <parent>
        <groupId>com.yh.csx.bsf</groupId>
        <artifactId>csx-bsf-dependencies</artifactId>
        <version>1.7.1-SNAPSHOT</version>
    </parent>
```
    或者引入依赖 csx-bsf-starter
```
    <dependency>
        <groupId>com.yh.csx.bsf</groupId>
        <artifactId>csx-bsf-dependencies</artifactId>
        <version>1.7.1-SNAPSHOT</version>
        <type>pom</type>
        <scope>import</scope>
    </dependency>
    <dependency>
       <artifactId>csx-bsf-starter</artifactId>
       <groupId>com.yh.csx.bsf</groupId>
       <version>1.7.1-SNAPSHOT</version>
    </dependency>
```

2. Demo程序  
    框架的使用demo，请参考[csx-bsf-demo](csx-bsf-demo)  
    各个组件的使用，请参考相关模块文档。

3. 参考properties配置文件  
    [resources/application.properties](resources/application.properties)

## 参与贡献
架构师: 车江毅  
开发: 黄兆平,刘建强  
维护: 王志斌  

##### by 车江毅