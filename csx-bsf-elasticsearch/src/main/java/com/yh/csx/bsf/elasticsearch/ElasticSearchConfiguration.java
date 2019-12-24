package com.yh.csx.bsf.elasticsearch;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;
import com.google.common.collect.Lists;
import com.yh.csx.bsf.core.base.BsfEnvironmentEnum;
import com.yh.csx.bsf.core.config.BsfConfiguration;
import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.core.util.PropertyUtils;
import com.yh.csx.bsf.elasticsearch.impl.ElasticSearchProvider;
import com.yh.csx.bsf.elasticsearch.impl.ElasticSearchSqlProvider;
import lombok.val;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import java.net.InetAddress;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
@Import(value = {BsfConfiguration.class})
@ConditionalOnProperty(name = "bsf.elasticsearch.enabled", havingValue = "true")
public class ElasticSearchConfiguration implements InitializingBean {

	@Autowired
	private ElasticSearchProperties elasticSearchProperties;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtils.info(ElasticSearchConfiguration.class,ElasticSearchProperties.Project,"连接初始化成功!!! "+ElasticSearchProperties.BsfElasticSearchServerAddrs+"=" + elasticSearchProperties.getServerAddrs());
	}

	@Bean(name = "bsfEsTransportClient",destroyMethod = "close")
	@Lazy
	public TransportClient getTransportClient() throws Exception{
		var serverAddrs=elasticSearchProperties.getServerAddrs();
		if (StringUtils.isEmpty(serverAddrs)) {
			serverAddrs = PropertyUtils.getPropertyCache(BsfEnvironmentEnum.ES_DEV.getServerkey(),"");
		}

		String[] serverArr = serverAddrs.trim().split(",");
		if (serverArr != null && serverArr.length > 0) {
			System.setProperty("es.set.netty.runtime.available.processors", "false");
			// client初始化
			Settings settings = Settings.builder().put("client.transport.ignore_cluster_name", true).build();
			val transportClient = new PreBuiltTransportClient(settings, Lists.newArrayList());
			for (String serverAddr : serverArr) {
				String[] inetArr = serverAddr.split(":");
				transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(inetArr[0]), Integer.valueOf(inetArr[1])));
			}
			return transportClient;
		}
		return null;
	}

	@Bean(name="bsfEsDruidDataSource",destroyMethod = "close")
	@Lazy
	public DruidDataSource getDruidDataSource() throws Exception{
		var serverAddrs=elasticSearchProperties.getServerAddrs();
		if (org.springframework.util.StringUtils.isEmpty(serverAddrs)) {
			serverAddrs = PropertyUtils.getPropertyCache(BsfEnvironmentEnum.ES_DEV.getServerkey(),"");
		}

		serverAddrs = serverAddrs.trim();
		Properties properties = new Properties();
		properties.put("url", "jdbc:elasticsearch://" + serverAddrs);
		properties.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES, "client.transport.ignore_cluster_name=true");
		return  (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
	}
	
	@Bean(destroyMethod = "close")
	@Lazy
    public ElasticSearchProvider ElasticSearchProvider(TransportClient bsfEsTransportClient) {
        return new ElasticSearchProvider(bsfEsTransportClient);
    }

	@Bean(destroyMethod = "close")
	@Lazy
	public ElasticSearchSqlProvider ElasticSearchSqlProvider(TransportClient bsfEsTransportClient,DruidDataSource bsfEsDruidDataSource) {
		return new ElasticSearchSqlProvider(elasticSearchProperties,bsfEsTransportClient,bsfEsDruidDataSource);
	}

}
