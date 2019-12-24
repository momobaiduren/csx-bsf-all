package com.yh.csx.bsf.core.util;

import lombok.var;

/**
 * 	报警工具类
 * @author Robin.Wang
 * @date	2019-10-16
 * */
public class WarnUtils {
	public static final String ALARM_ERROR	= "ERROR";	//错误报警
	public static final String ALARM_WARN 	= "WARN";	//警告
	public static final String ALARM_INFO 	= "WARN";	//通知
	/**
	 * 	即时发送报警通知
	 * 	@param type 报警类型 {
	 * 	@param title 报警标题
	 * 	@param content 报警内容
	 * */
	public static void notifynow(String alarm_type, String title, String content) {		
		notify(alarm_type, title, content,true);
	}
	
	/**
	 * 	发送报警
	 * @param type	告警类型
	 * @param title	告警标题
	 * @param content 告警内容
	 * */
	public static void notify(String alarm_type, String title, String content) {
		notify(alarm_type, title, content,false);
	}
	/**
	 * 	发送报警
	 * @param type	告警类型
	 * @param title	告警标题
	 * @param content 告警内容
	 * @param isNow 是否即时发送
	 * */
	public static void notify(String alarm_type, String title, String content,boolean isNow) {
		Class clazz = ReflectionUtils.classForName("com.yh.csx.bsf.health.warn.WarnProvider");
		if (clazz != null) {
			var bean = ContextUtils.getBean(clazz, false);
			if (bean != null) {
				if(isNow){
					ReflectionUtils.callMethodWithParams(bean, "notifynow", new String[] { alarm_type, title, content },String.class, String.class, String.class);
				} else {
					ReflectionUtils.callMethodWithParams(bean, "notify", new String[] { alarm_type, title, content },String.class, String.class, String.class);
				}
			}
		}
	}
	
}
