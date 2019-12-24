package com.yh.csx.bsf.mq.base;

/**
 * @author: chejiangyi
 * @version: 2019-06-12 14:49 生产者提供者:提供不同类型队列的生产者
 **/
public class AbstractProducerProvider extends AbstractProvider {
	
	public <T> AbstractProducer sendMessage(String topic, String tag, T msg) {
		return null;
	}
}
