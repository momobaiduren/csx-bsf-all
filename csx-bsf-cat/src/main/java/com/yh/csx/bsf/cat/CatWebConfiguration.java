package com.yh.csx.bsf.cat;

import com.yh.csx.bsf.cat.remote.CatCrossFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.dianping.cat.servlet.CatFilter;
import com.yh.csx.bsf.core.util.LogUtils;

/**
 * @author: chejiangyi
 * @version: 2019-07-04 10:57
 **/
@org.springframework.context.annotation.Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "bsf.cat.enabled", havingValue = "true")
public class CatWebConfiguration {
    @Bean
    @ConditionalOnProperty(name = "bsf.cat.filter", havingValue = "default",matchIfMissing = true)
    public FilterRegistrationBean<CatFilter> catFilter() {
        FilterRegistrationBean<CatFilter> registration = new FilterRegistrationBean<>();
        CatFilter filter = new CatFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        LogUtils.info(CatWebConfiguration.class,CatProperties.Project,"url拦截注册成功");
        return registration;
    }

    @Bean
    @ConditionalOnProperty(name = "bsf.cat.filter", havingValue = "cross")
    public FilterRegistrationBean<CatCrossFilter> catCrossFilter() {
        FilterRegistrationBean<CatCrossFilter> registration = new FilterRegistrationBean<>();
        CatCrossFilter filter = new CatCrossFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-cross-filter");
        registration.setOrder(1);
        LogUtils.info(CatWebConfiguration.class,CatProperties.Project,"cross url拦截注册成功");
        return registration;
    }
    
    @Bean
    @ConditionalOnProperty(name = "bsf.cat.filter", havingValue = "cross")
    public CatFeignRpcAspect catCrossAspect() {
    	return new CatFeignRpcAspect();
    }
}




