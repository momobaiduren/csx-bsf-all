package com.yh.csx.bsf.mq.rocketmq;

import com.yh.csx.bsf.core.serialize.JsonSerializer;
import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.mq.base.AbstractConsumer;
import com.yh.csx.bsf.mq.base.AbstractConsumerProvider;
import com.yh.csx.bsf.mq.base.MQException;
import com.yh.csx.bsf.mq.base.Message;
import com.yh.csx.bsf.mq.base.SubscribeRunable;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: chejiangyi
 * @version: 2019-06-12 13:01
 * rocketmq的消费者封装使用提供类
 **/
public class RocketMQConsumerProvider extends AbstractConsumerProvider{

    @Autowired
    RocketMQProperties rocketMQProperties;

    @Override
    public <T> AbstractConsumer subscribe(String consumergroup,String topic,String[] filterTags,SubscribeRunable<T> runnable,Class<T> type) {
        DefaultMQPushConsumer consumer =null;
        try {
            consumer = new DefaultMQPushConsumer(consumergroup);            
            // Specify name server addresses.
            consumer.setNamesrvAddr(rocketMQProperties.getNamesrvaddr());
            if(rocketMQProperties.getIsUseVIPChannel()!=null) {
            	consumer.setVipChannelEnabled(rocketMQProperties.getIsUseVIPChannel());
            }
            String filtertag = null;
            // Subscribe one more more topics to consume.
            if(filterTags ==null || filterTags.length==0) {
                filtertag="*";
            }
            else
            {
                filtertag= StringUtils.join( filterTags,"||");
            }
            consumer.subscribe(topic, filtertag);
            // Register callback to execute on arrival of messages fetched from brokers.
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    MessageExt messageExt = msgs.get(0);
                    try {
                    	String id=messageExt.getMsgId();
                        byte[] body = messageExt.getBody();
                        String tags = messageExt.getTags();
                        String topic = messageExt.getTopic();
                        String keys = messageExt.getKeys();
//                        ParameterizedType type = (ParameterizedType)(runnable.getClass().getGenericInterfaces()[0]);
//                        Class<T> tClass = (Class<T>)type.getActualTypeArguments()[0];
                        String msg = new String(body, RemotingHelper.DEFAULT_CHARSET);
                        RocketMQMonitor.hook().run("consume", () -> {
                            if(type == String.class)
                            {
                                runnable.run(new Message<T>(id,topic,tags, (T)msg));
                            }
                            else
                            {
                                runnable.run(new Message<T>(id,topic,tags, new JsonSerializer().deserialize(msg,type)));
                            }
                            return null;
                        });
                        //System.out.println("body:" + new String(body, RemotingHelper.DEFAULT_CHARSET) + " tags:" + tags + " topic:" + topic + " keys:" + keys);
                    } catch (Exception e) {
                        LogUtils.error(RocketMQConsumerProvider.class,RocketMQProperties.Project,String.format("rocketmq 消费者%s,消费异常",consumergroup),e);
                        //处理出现异常，获取重试次数.达到某个次数的时候可以记录日志，做补偿处理
                        int reconsumeTimes = messageExt.getReconsumeTimes();
                        if (reconsumeTimes < rocketMQProperties.getReconsumeTimes()) {
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            //Launch the consumer instance.
            consumer.start();
            AbstractConsumer abstractConsumer = new AbstractConsumer();
            abstractConsumer.setObject(consumer);
            LogUtils.info(RocketMQConsumerProvider.class,RocketMQProperties.Project,String.format("rocketmq 消费者%s,队列%s 启动成功",consumergroup,topic));
            return abstractConsumer;
            //System.out.printf("Consumer Started.%n");
        }
        catch (Exception exp)
        {
            LogUtils.error(RocketMQConsumerProvider.class,RocketMQProperties.Project,String.format("rocketmq 消费者%s,队列%s 启动失败",consumergroup,topic),exp);
            if(consumer!=null)
            {
                consumer.shutdown();
                consumer = null;
            }
            throw new MQException(exp);
        }

    }

    @Override
    public void close() {

        super.close();
    }
}
