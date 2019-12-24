package com.yh.csx.bsf.message.sms;


import lombok.Getter;
import lombok.var;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: chejiangyi
 * @version: 2019-05-31 13:18
 **/
@Configuration
@EnableConfigurationProperties(SmsProperties.class)
@ConditionalOnProperty(name = "bsf.message.sms.enabled", havingValue = "true")
@Getter
public class SmsConfiguration  implements InitializingBean {
    @Autowired
    SmsProperties smsProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        var smsUrl = smsProperties.getSmsUrl();
        smsProperties.setSmsUrl(smsUrl.endsWith("/") ? smsUrl.substring(0, smsUrl.length() - 1) : smsUrl);
    }

    @Bean
    public SmsProvider getSms()
    {
        return new SmsProvider(smsProperties);
    }
}
