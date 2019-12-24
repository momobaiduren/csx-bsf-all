package com.yh.csx.bsf.core.initializer;

import com.yh.csx.bsf.core.config.CoreProperties;
import com.yh.csx.bsf.core.util.FileUtils;
import com.yh.csx.bsf.core.util.JsonUtils;
import com.yh.csx.bsf.core.util.LogUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 容器生命周期监听程序
 * */
@Order
public class CoreApplicationRunner implements org.springframework.boot.ApplicationRunner {

	@Override
	public void run(ApplicationArguments var1) throws Exception {
		saveStatus("STARTED");
	}

	private void saveStatus(String status){
		HashMap<String,Object> map = new HashMap<>(2);
		map.put("data",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		map.put("state",status);
		FileUtils.writeAllText("status", JsonUtils.serialize(map));
		LogUtils.info(CoreApplicationRunner.class, CoreProperties.Project,"应用已正常启动!");
	}

}
