package com.yh.csx.bsf.core.config;

import com.yh.csx.bsf.core.filter.WebContextFilter;
import com.yh.csx.bsf.core.util.LogUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author: chejiangyi
 * @version: 2019-07-01 19:07
 **/
@Configuration
@ConditionalOnWebApplication
public class WebConfiguration {
    @Bean
    @ConditionalOnProperty(name = "bsf.web.context.enabled", havingValue = "true",matchIfMissing = true)
    public FilterRegistrationBean webContextFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //优先注入
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.setFilter(new WebContextFilter());
        filterRegistrationBean.setName("webContextFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        LogUtils.info(WebConfiguration.class,CoreProperties.Project,"web请求上下文过滤器注册成功");
        return filterRegistrationBean;
    }
}
