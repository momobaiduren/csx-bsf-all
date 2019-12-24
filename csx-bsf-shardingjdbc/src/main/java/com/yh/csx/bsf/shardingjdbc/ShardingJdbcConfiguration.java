package com.yh.csx.bsf.shardingjdbc;
import com.google.common.base.Preconditions;
import com.yh.csx.bsf.core.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.core.exception.ShardingException;
import org.apache.shardingsphere.core.util.InlineExpressionParser;
import org.apache.shardingsphere.core.yaml.swapper.impl.EncryptRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.core.yaml.swapper.impl.MasterSlaveRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.core.yaml.swapper.impl.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.encrypt.SpringBootEncryptRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.util.DataSourceUtil;
import org.apache.shardingsphere.shardingjdbc.spring.boot.util.PropertyUtil;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

import javax.sql.DataSource;
import java.beans.ConstructorProperties;
import java.sql.SQLException;
import java.util.*;


/**
 * @author: chejiangyi
 * @version: 2019-05-31 13:18
 **/
@Configuration
//@AutoConfigureAfter(SpringBootConfiguration.class)
@EnableConfigurationProperties({ShardingJdbcProperties.class,SpringBootShardingRuleConfigurationProperties.class, SpringBootMasterSlaveRuleConfigurationProperties.class, SpringBootEncryptRuleConfigurationProperties.class, SpringBootPropertiesConfigurationProperties.class})
@ConditionalOnProperty(name = "bsf.shardingjdbc.enabled", havingValue = "true")
public class ShardingJdbcConfiguration extends SpringBootConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext var1) throws BeansException
    {
       super.setEnvironment(var1.getEnvironment());
       LogUtils.info(ShardingJdbcConfiguration.class,ShardingJdbcProperties.Project,"已启动!!!");
    }

    @ConstructorProperties({"shardingProperties", "masterSlaveProperties", "encryptProperties", "propMapProperties"})
    public ShardingJdbcConfiguration(SpringBootShardingRuleConfigurationProperties shardingProperties, SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties, SpringBootEncryptRuleConfigurationProperties encryptProperties, SpringBootPropertiesConfigurationProperties propMapProperties) {
       super(shardingProperties,masterSlaveProperties,encryptProperties,propMapProperties);
    }

    @Bean
    @ConditionalOnProperty(name = "bsf.shardingjdbc.aspect.enabled", havingValue = "true")
    @ConditionalOnClass(name = "org.aspectj.lang.annotation.Aspect")
    public ShardingJdbcDynamicDataSourceAspect shardingJdbcDynamicDataSourceAspect(){
        return new ShardingJdbcDynamicDataSourceAspect();
    }
}
