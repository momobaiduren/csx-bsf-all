package com.yh.csx.bsf.message.sms;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-08-12 15:08
 **/
@ConfigurationProperties
@Data
public class SmsProperties {
    @Value("${sms.url}")
    private String smsUrl;

    @Value("${sms.url.bak:}")
    private String smsUrlBak;
    
    @Value("${sms.user}")
    private String smsUser;

    @Value("${sms.password}")
    private String smsPassword;

    @Value("${sms.server}")
    private String smsServer;
}
