package com.yh.csx.bsf.mq.base;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author: chejiangyi
 * @version: 2019-06-12 14:49
 * 消费者提供者: 提供不同类型的消息队列的消费者
 **/
public class AbstractConsumerProvider extends AbstractProvider {
    public  <T> AbstractConsumer subscribe(String consumergroup, String topic, String[] filterTags, SubscribeRunable<T> runnable, Class<T> type) {return null;}
}

