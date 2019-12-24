package com.yh.csx.bsf.eureka.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-09-25 14:11
 **/
@ConfigurationProperties
@Data
public class KongProperties {
    @Value("${bsf.eureka.kong.ip:}")
    private String kongIp;
    
    @Value("${bsf.eureka.kong.ip.exgex:}")
    private String kongIpExgex;
    
    @Value("${bsf.eureka.kong.port:8001}")
    private int kongPort;

    @Value("${spring.application.name:}")
    private String springApplicationName;

    @Value("${server.servlet.context-path:}")
    private String serverServletContextPath;

    @Value("${bsf.eureka.kong.healthchecks.active.http_path:}")
    private String healthChecksHttpPath;

    @Value("${bsf.eureka.kong.healthchecks.active.unhealthy.tcp_failures:1}")
    private String healthChecksUnhealthyTcpFailures;
    @Value("${bsf.eureka.kong.healthchecks.active.unhealthy.timeouts:1}")
    private String healthChecksUnhealthyTimeouts;
    @Value("${bsf.eureka.kong.healthchecks.active.unhealthy.http_failures:1}")
    private String healthChecksUnhealthyHttpFailures;
    @Value("${bsf.eureka.kong.healthchecks.active.unhealthy.interval:5}")
    private String healthChecksUnhealthyInterval;

    @Value("${bsf.eureka.kong.healthchecks.active.healthy.successes:1}")
    private String healthChecksHealthySuccesses;
    @Value("${bsf.eureka.kong.healthchecks.active.healthy.interval:5}")
    private String healthChecksHealthyInterval;

}
