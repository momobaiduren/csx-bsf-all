package com.yh.csx.bsf.elk;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 14:05
 **/
@ConfigurationProperties
@Data
public class ElkProperties {
    @Value("${bsf.elk.destinations:}")
    private String[] destinations;

    @Value("${bsf.elk.appName:}")
    private String appName;

    @Value("${spring.application.name:}")
    private String springAppName;

    public static String Prefix="bsf.elk.";
    public static String Project="Elk";
    public static String Destinations="bsf.elk.destinations";
    public static String BsfWebControllerAspectEnabled="bsf.elk.web.controller.aspect.enabled";
}
