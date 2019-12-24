package com.yh.csx.bsf.mq.base;

import com.yh.csx.bsf.core.system.ProcessExitEvent;
import com.yh.csx.bsf.core.util.LogUtils;
import com.yh.csx.bsf.mq.MQProperties;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;

import java.lang.reflect.Method;

/**
 * @author: chejiangyi
 * @version: 2019-06-12 13:19
 * 消息队列对象抽象
 **/
@Data
public class AbstractMQ implements AutoCloseable {

    public AbstractMQ()
    {
        ProcessExitEvent.register(()->{
            try {
                this.close();
            }
            catch (Exception exp)
            {
                LogUtils.warn(AbstractMQ.class, MQProperties.Project,"应用退出时释放mq资源异常",exp);
            }
        },1,false);
    }
    private Object object;
    @Override
    public void close(){

    }

    protected void innerClose(Object object)
    {
        try {
            if(object!=null) {
                LogUtils.debug(AbstractMQ.class,MQProperties.Project,"MQ准备释放内部资源...");
                String[] methods = "close,shutdown".split(",");
                for (String name : methods) {
                    Method method = object.getClass().getDeclaredMethod(name);
                    if(method!=null)
                    {method.invoke(object, new Object[]{});}
                }
            }
        }
        catch (Exception exp)
        {
            LogUtils.warn(AbstractMQ.class,MQProperties.Project,"MQ内部资源释放异常",exp);
        }
    }
}
