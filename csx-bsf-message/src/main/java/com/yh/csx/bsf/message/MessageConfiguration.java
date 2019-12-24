package com.yh.csx.bsf.message;

import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.message.dingding.DingdingConfiguration;
import com.yh.csx.bsf.message.sms.SmsConfiguration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @author: chejiangyi
 * @version: 2019-05-31 13:18
 **/
@Configuration
@ConditionalOnProperty(name = "bsf.message.enabled", havingValue = "true")
@EnableConfigurationProperties({MessageProperties.class})
@Import({SmsConfiguration.class,DingdingConfiguration.class})
public class MessageConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(MessageConfiguration.class,MessageProperties.Project,"已启动!!!");
    }
}
