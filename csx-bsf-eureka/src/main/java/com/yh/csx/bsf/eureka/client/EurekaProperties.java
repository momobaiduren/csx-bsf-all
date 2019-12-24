package com.yh.csx.bsf.eureka.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 14:34
 **/
@ConfigurationProperties
@Data
public class EurekaProperties {
    @Value("${eureka.client.serviceUrl.defaultZone:}")
    private String defaultZone;
    public static String Prefix="bsf.eureka.";
    public static String Prefix2="eureka.";
    public static String Project="Eureka";
    public static String ClientProject="Eureka-Client";
    public static String StringApplicationName="spring.application.name";
    public static String BsfEurekaClientEnabled="bsf.eureka.client.enabled";
    public static String EurekaClientEnabled="eureka.client.enabled";
    public static String EurekaClientServiceUrlDefaultZone="eureka.client.serviceUrl.defaultZone";
    public static String EurekaInstanceLeaseExpirationDurationInSencodes="eureka.instance.lease-expiration-duration-in-seconds";
    public static String EurekaInstanceLeaseRenwalIntervalInSeconds="eureka.instance.lease-renewal-interval-in-seconds";
    public static String EurekaInstancePerferIpAddress="eureka.instance.prefer-ip-address";
    public static String SpringMainAllowBeanDefinitionOverriding="spring.main.allow-bean-definition-overriding";
    public static String RibbonReadTimeOut="ribbon.ReadTimeout";
    public static String RibbonConnectTimeOut="ribbon.ConnectTimeout";

}
