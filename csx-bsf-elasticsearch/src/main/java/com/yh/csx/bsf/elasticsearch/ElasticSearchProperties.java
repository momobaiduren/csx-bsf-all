package com.yh.csx.bsf.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 12:17
 **/
@ConfigurationProperties
public class ElasticSearchProperties {
    // es服务地址,多个用逗号分隔
    @Value("${bsf.elasticsearch.serverAddrs:}")
    @Getter @Setter
    private String serverAddrs;

    // es批提交最小数量
    @Value("${bsf.elasticsearch.bulkSize:2000}")
    @Getter @Setter
    private Integer bulkSize;

    public static String Prefix="bsf.elasticsearch.";
    public static String Project="ElasticSearch";
    public static String BsfElasticSearchServerAddrs="bsf.elasticsearch.serverAddrs";
    public static String BsfElasticSearchBulkSize="bsf.elasticsearch.bulkSize";
    public static String SpringApplicationName="spring.application.name";
    public static String ManagementHealthElasticSearchEnabled = "management.health.elasticsearch.enabled";
}
