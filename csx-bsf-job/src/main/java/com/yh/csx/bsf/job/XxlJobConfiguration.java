package com.yh.csx.bsf.job;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.yh.csx.bsf.core.config.BsfConfiguration;
import com.yh.csx.bsf.core.base.BsfEnvironmentEnum;
import com.yh.csx.bsf.core.base.BsfException;
import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.core.util.NetworkUtils;
import com.yh.csx.bsf.core.util.PropertyUtils;
import com.yh.csx.bsf.core.util.StringUtils;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Huang Zhaoping
 */
@Configuration
@ConditionalOnProperty(name = "bsf.job.enabled", havingValue = "true")
@EnableConfigurationProperties(XxlProperties.class)
@Import(BsfConfiguration.class)
public class XxlJobConfiguration {

	@Autowired
	XxlProperties xxlProperties;

	@Bean(initMethod = "start", destroyMethod = "destroy")
	public XxlJobSpringExecutor xxlJobExecutor() {
		String appName = xxlProperties.getAppName().length() == 0 ? xxlProperties.getSpringAppName()
				: xxlProperties.getAppName();
		if (appName.length() == 0) {
			throw new BsfException("缺少参数：xxl.job.executor.appname");
		}
		var adminAddresses = xxlProperties.getAdminAddresses();
		// 设置默认ip
		if (adminAddresses.length() == 0) {
			adminAddresses = PropertyUtils.getPropertyCache(BsfEnvironmentEnum.JOB_DEV.getServerkey(), "");
		}
		XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
		executor.setAdminAddresses(adminAddresses);
		executor.setAppName(appName);
		executor.setIp(xxlProperties.getIp());
		if(StringUtils.isEmpty(xxlProperties.getIp())) {
			executor.setIp(NetworkUtils.getIpAddress());
		}	
		executor.setPort(xxlProperties.getPort());
		executor.setAccessToken(xxlProperties.getAccessToken());
		executor.setLogPath(xxlProperties.getLogPath());
		executor.setLogRetentionDays(xxlProperties.getLogRetentionDays());
		LogUtils.info(XxlJobConfiguration.class, "Job","已启动!!! " + XxlProperties.XxlJobAdminAddresses + "=" + adminAddresses);
		return executor;
	}
	

}
