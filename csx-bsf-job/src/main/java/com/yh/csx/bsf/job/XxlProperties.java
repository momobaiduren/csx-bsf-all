package com.yh.csx.bsf.job;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 15:02
 **/
@ConfigurationProperties
@Data
public class XxlProperties {
    @Value("${spring.application.name:}")
    private String springAppName;

    @Value("${xxl.job.admin.addresses:}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname:}")
    private String appName;

    @Value("${xxl.job.executor.ip:}")
    private String ip;
    //匹配网段
    @Value("${xxl.job.executor.ip.regex:}")    
    private String ipRegx;
    //排除网段
    @Value("${xxl.job.executor.ip.exgex:}")
    private String ipExgx;
    
    @Value("${xxl.job.executor.port:9999}")
    private int port;

    @Value("${xxl.job.accessToken:}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath:job-logs}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays:7}")
    private int logRetentionDays;

    public static String XxlJobAdminAddresses="xxl.job.admin.addresses";
}
