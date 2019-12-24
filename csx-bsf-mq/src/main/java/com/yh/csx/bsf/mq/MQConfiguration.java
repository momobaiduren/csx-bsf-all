package com.yh.csx.bsf.mq;

import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.mq.rocketmq.RocketMQConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @author: chejiangyi
 * @version: 2019-06-12 12:02
 **/
@Configuration
@ConditionalOnProperty(name = "bsf.mq.enabled", havingValue = "true")
@EnableConfigurationProperties(MQProperties.class)
@Import(RocketMQConfiguration.class)
public class MQConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(MQConfiguration.class,MQProperties.Project,"已启动!!!");
    }

}
