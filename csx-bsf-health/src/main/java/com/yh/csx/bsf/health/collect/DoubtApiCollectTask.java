package com.yh.csx.bsf.health.collect;

import com.yh.csx.bsf.core.common.Collector;
import com.yh.csx.bsf.core.util.PropertyUtils;
import com.yh.csx.bsf.health.base.AbstractCollectTask;
import com.yh.csx.bsf.health.base.FieldReport;
import com.yh.csx.bsf.health.base.HealthException;

import com.yh.csx.bsf.health.filter.DoubtApiInterceptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Robin.Wang
 * @version 1.0.0
 */
public class DoubtApiCollectTask extends AbstractCollectTask {

	@Override
	public int getTimeSpan() {
		return PropertyUtils.getPropertyCache("bsf.health.doubtapi.timeSpan", 20);
	}

	@Override
	public boolean getEnabled() {
		return PropertyUtils.getPropertyCache("bsf.health.doubtapi.enabled", false);
	}

	@Override
	public String getDesc() {
		return "可疑API采集";
	}

	@Override
	public String getName() {
		return "doubtapi.info";
	}

	@Override
	protected Object getData() {

		ApiUsedMemoryTopInfo info = new ApiUsedMemoryTopInfo();
		try {
			val map = (Map<String, DoubtApiInterceptor.DoubtApiInfo>) Collector.Default.value("bsf.doubtapi.info").get();
			if (map != null && map.size() > 0) {
				val copy = map.values().toArray(new DoubtApiInterceptor.DoubtApiInfo[map.values().size()]);
				Arrays.sort(copy);
				int detailLen = copy.length > 5 ? 5 : copy.length;
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < detailLen; i++) {
					val o = copy[i];
					
					long avg=0;
					if(o.getCount()>0) {
						avg= o.getTotalIncreMem() / 1024 / 1024/ o.getCount();
					}else {
						avg=o.getTotalIncreMem()/ 1024 / 1024;
					}					
					sb.append(String.format("url:%s,方法:%s,平均内存增量:%s(M),调用次数:%s\r\n", o.getUri(), o.getMethod(),
							avg, o.getCount()));
				}
				info.detail = sb.toString();
			}
		} catch (Exception exp) {
			// throw new HealthException(exp);
		}
		return info;

	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ApiUsedMemoryTopInfo {
		@FieldReport(name = "doubt.api.detail", desc = "可疑内存增长api分析报告")
		String detail;
	}

}
