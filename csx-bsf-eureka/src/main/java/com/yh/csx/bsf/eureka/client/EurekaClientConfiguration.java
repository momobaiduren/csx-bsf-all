package com.yh.csx.bsf.eureka.client;

import com.yh.csx.bsf.core.config.BsfConfiguration;
import com.yh.csx.bsf.core.util.LogUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

/**
 * @author: chejiangyi
 * @version: 2019-06-15 14:50
 **/
@Configuration
@ConditionalOnProperty(name = "bsf.eureka.client.enabled", havingValue = "true")
@Import(BsfConfiguration.class)
@EnableConfigurationProperties({EurekaProperties.class,KongProperties.class})
@EnableEurekaClient
//@EnableFeignClients
public class EurekaClientConfiguration implements InitializingBean {
    @Autowired
    EurekaProperties eurekaProperties;

    @Override
    public void afterPropertiesSet() {
        LogUtils.info(EurekaClientConfiguration.class,EurekaProperties.ClientProject,"已启动!!!"+" "+EurekaProperties.EurekaClientServiceUrlDefaultZone+"="+ eurekaProperties.getDefaultZone());
    }
    @Bean
    @ConditionalOnClass(name = "com.yh.csx.bsf.health.collect.HealthCheckProvider")
    @Lazy
    public EurekaMonitor eurekaClientMonitor(){
        return new EurekaMonitor();

    }
    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnProperty(name = "bsf.eureka.kong.enabled", havingValue = "true")
    public KongCheckHealthProvider kongCheckHealthProvider(KongProperties kongProperties){
        return new KongCheckHealthProvider(kongProperties);
    }

}
