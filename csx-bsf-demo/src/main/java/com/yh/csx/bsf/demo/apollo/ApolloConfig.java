package com.yh.csx.bsf.demo.apollo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: chejiangyi
 * @version: 2019-05-28 09:46
 **/
@Configuration
public class ApolloConfig implements InitializingBean {
    @Value("${spring.application.name:}")
    private String springAppName;

    @Value("${aaa:}")
    private String aaa;

    @Override
    public void afterPropertiesSet() throws Exception {
        String aaa1=aaa;
    }
}
