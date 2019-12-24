package com.yh.csx.bsf.shardingjdbc;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 15:35
 **/
@ConfigurationProperties
public class ShardingJdbcProperties {
    public static String Project="Sharding-jdbc";
    public static String SpringShardingSphereEnabled="spring.shardingsphere.enabled";
    public static String SpringApplicationName="spring.application.name";
}
