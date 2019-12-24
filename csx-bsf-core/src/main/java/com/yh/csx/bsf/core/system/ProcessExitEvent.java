package com.yh.csx.bsf.core.system;

import com.yh.csx.bsf.core.base.Callable;
import com.yh.csx.bsf.core.config.CoreProperties;
import com.yh.csx.bsf.core.util.LogUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;

import java.util.ArrayList;

/**
 * @author: chejiangyi
 * @version: 2019-09-25 19:59
 * 全局进程关闭事件定义
 **/
public class ProcessExitEvent {
    private static ArrayList<ExitCallback> callBackList = new ArrayList<>();
    private static Object lock = new Object();

    /**
     *
     * @param action0
     * @param order 越大越晚 必须大于0
     */
    public static void register(Callable.Action0 action0,int order,Boolean asynch){
        synchronized (lock){
            callBackList.add(new ExitCallback(action0,Math.abs(order),asynch));
        }
    }
    static {
        //JVM 停止或重启时
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try{
                synchronized (lock){
                    callBackList.sort((c1,c2)->c1.order-c2.order);
                    for(val a:callBackList) {
                        Callable.Action0 method = ()-> {
                            try {
                                a.action0.invoke();
                            } catch (Exception e2) {
                                LogUtils.error(ProcessExitEvent.class, CoreProperties.Project, "进程关闭事件回调处理出错", e2);
                            }
                        };
                        if(a.asynch){
                            new Thread(()->{method.invoke();}).start();
                        }else{
                            method.invoke();
                        }
                    }
                }
                LogUtils.info(ProcessExitEvent.class, CoreProperties.Project,"应用已正常退出！");
            }
            catch(Exception e){ LogUtils.error(ProcessExitEvent.class, CoreProperties.Project,"进程关闭事件回调处理出错",e); }
        }));
    }
    @Data
    @AllArgsConstructor
    private static class  ExitCallback{
        Callable.Action0 action0;
        /**顺序*/
        Integer order;
        /**异步支持*/
        Boolean asynch=false;
    }
}
