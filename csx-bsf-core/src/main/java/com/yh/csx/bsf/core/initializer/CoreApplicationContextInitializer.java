package com.yh.csx.bsf.core.initializer;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import com.yh.csx.bsf.core.base.BsfEnvironmentEnum;
import com.yh.csx.bsf.core.base.BsfException;
import com.yh.csx.bsf.core.common.PropertyCache;
import com.yh.csx.bsf.core.config.CoreProperties;
import com.yh.csx.bsf.core.util.*;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Random;
import java.util.UUID;

/**
 * @author: chejiangyi
 * @version: 2019-06-14 19:02
 **/
public class CoreApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        if(ContextUtils.MainClass ==null) {
            ContextUtils.MainClass = deduceMainApplicationClass();
        }
        ContextUtils.setApplicationContext(context);
        ConfigurableEnvironment environment = context.getEnvironment();
        if("false".equalsIgnoreCase(environment.getProperty(CoreProperties.BsfEnabled))){
            return;
        }
        //环境变量初始化
        String propertyValue = environment.getProperty(CoreProperties.SpringApplicationName);
        String propertyValue2 = environment.getProperty(CoreProperties.BsfEnv);
        if (!Strings.isEmpty(propertyValue)&&!Strings.isEmpty(propertyValue2)) {
            //optimizeJson(environment);
            optimize(environment);
            //LogUtils.info(CoreApplicationContextInitializer.class,CoreProperties.Project,CoreProperties.SpringApplicationName+"="+propertyValue);
            setDefaultProperty(CoreProperties.SpringApplicationName,propertyValue,"");
            LogUtils.info(CoreApplicationContextInitializer.class,CoreProperties.Project,CoreProperties.BsfEnv+"="+propertyValue2);
            for (BsfEnvironmentEnum e2 : BsfEnvironmentEnum.values()) {
                if (e2.getEnv().toString().equalsIgnoreCase(propertyValue2))
                {
                    setDefaultProperty(e2.getServerkey(),e2.getUrl(),"[bsf环境变量]");
                }
            }
        }

        optimizeLog(environment);
        this.registerContextRefreshEvent();
    }

    private Class<?> deduceMainApplicationClass() {
        try {
            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("main".equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        }
        catch (ClassNotFoundException ex) {
            // Swallow and continue
        }
        return null;
    }

    private void optimizeJson(ConfigurableEnvironment environment){
        //json 优化
        setDefaultProperty(CoreProperties.SpringJacksonDateFormat,"yyyy-MM-dd HH:mm:ss","[json 标准规范]");
        setDefaultProperty(CoreProperties.SpringJacksonTimeZone,"GMT+8","[json 标准规范]");
    }

    private void optimize(ConfigurableEnvironment environment)
    {
        /**启动全局优化默认配置*/
        double cpuCount = Runtime.getRuntime().availableProcessors();


        if(ContextUtils.isWeb()) {
            //tomcat 优化 * 核心数
            setDefaultProperty(CoreProperties.ServerTomcatMaxThreads, ((int) (200 * cpuCount)) + "", "[自动化调优]");
            setDefaultProperty(CoreProperties.ServerTomcatMaxConnections, ((int) (10000 * cpuCount)) + "", "[自动化调优]");
            setDefaultProperty(CoreProperties.ServerTomcatMinSpaceThreads, ((int) (20 * (cpuCount / 2))) + "", "[自动化调优]");
            //tomcat 传输优化
            //setDefaultProperty(CoreProperties.ServeCompressionEnabled, "true", "[自动化调优]");
            //setDefaultProperty(CoreProperties.ServeCompressionMimeTypes, "application/json,application/xml,text/html,text/xml,text/plain", "[自动化调优]");
        }
        //setDefaultProperty("server.tomcat.accept-count","200");
        //setDefaultProperty("server.connection-timeout","");
    }

    private void optimizeLog(ConfigurableEnvironment environment)
    {
        String message="[日志标准规范]";
        //MQ客户端日志目录
        setDefaultProperty("rocketmq.client.logRoot","log",message);
        //文件优化
        setDefaultProperty(CoreProperties.LoggingFile,"log/app.log",message);
        setDefaultProperty(CoreProperties.LoggingFileMaxHistory,"3",message);
        setDefaultProperty(CoreProperties.LoggingFileMaxSize,"10MB",message);
        //日志优化最大
        ILoggerFactory factory = LoggerFactory.getILoggerFactory();
        if(factory!=null&&factory instanceof LoggerContext)
        {
            val root = ((LoggerContext)factory).getLogger("ROOT");
            if(root!=null)
            {
                val file = root.getAppender("FILE");
                if(file!=null && file instanceof RollingFileAppender)
                {
                    val rollingPolicy = ((RollingFileAppender)file).getRollingPolicy();
                    if(rollingPolicy!=null && rollingPolicy instanceof SizeAndTimeBasedRollingPolicy)
                    {
                        setDefaultProperty(CoreProperties.BsfLoggingFileTotalSize,"1GB",message);
                        ((SizeAndTimeBasedRollingPolicy)rollingPolicy).setTotalSizeCap(FileSize.valueOf(environment.getProperty(CoreProperties.BsfLoggingFileTotalSize,"1GB")));
                    }
                }
            }
        }
    }

    void setDefaultProperty(String key,String defaultPropertyValue,String message)
    {
        PropertyUtils.setDefaultInitProperty(CoreApplicationContextInitializer.class,CoreProperties.Project,key,defaultPropertyValue,message);
    }

    void registerContextRefreshEvent(){
        PropertyCache.Default.listenUpdateCache("通过配置刷新上下文监听",(data)->{
            if(data!=null&&data.size()>0){
                for(val e:data.entrySet()){
                    if(PropertyUtils.getPropertyCache(CoreProperties.BsfContextRestartEnabled,false)==false){
                        return;
                    }
                    if(e.getKey().equalsIgnoreCase(CoreProperties.BsfContextRestartText)){
                        refreshConetext();
                        return;
                    }
                }
            }
        });
    }

    void refreshConetext(){
        if(ContextUtils.getApplicationContext()!=null)
        {
            if(ContextUtils.MainClass ==null){
                LogUtils.error(CoreApplicationContextInitializer.class,CoreProperties.Project,"重启失败",new BsfException("检测到重启上下文事件,因无法找到启动类，重启失败!!!"));
                return;
            }
            val context = ContextUtils.getApplicationContext();
            ApplicationArguments args = context.getBean(ApplicationArguments.class);
            val waitTime = new Random(UUID.randomUUID().getMostSignificantBits()).nextInt(PropertyUtils.getPropertyCache(CoreProperties.BsfContextRestartTimeSpan,10));
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(waitTime);
                    context.stop();
                    context.close();
                    ReflectionUtils.findMethod( ContextUtils.MainClass,"main").invoke(null, new Object[]{args.getSourceArgs()});
                }
                catch (Exception exp)
                {
                    LogUtils.error(CoreApplicationContextInitializer.class,CoreProperties.Project,"重启失败",new BsfException("根据启动类"+ContextUtils.MainClass.getName()+"动态启动main失败"));
                }
            });
            thread.setDaemon(false);
            thread.start();
        }
    }

}
