package com.yh.csx.bsf.mq.rocketmq;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 15:17
 **/
@ConfigurationProperties
@Data
public class RocketMQProperties {
    @Value("${bsf.rocketmq.namesrvaddr:}")
    /**
     * namesrv地址 可以有多个，;分割
     */
    private String namesrvaddr;
    @Value("${bsf.rocketmq.reconsumetimes:3}")
    /**
     * 重试消费次数
     **/
    private Integer reconsumeTimes;
    /**
     * 是否启用VIP通道
     * */
    @Value("${bsf.rocketmq.isUseVIPChannel:false}")
    private Boolean isUseVIPChannel;
    
    public static String Project="RocketMQ";
    public static String BSfRocketMQNameSrvaddr="bsf.rocketmq.namesrvaddr";
}
