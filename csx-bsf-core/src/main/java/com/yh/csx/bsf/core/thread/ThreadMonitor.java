package com.yh.csx.bsf.core.thread;

import com.yh.csx.bsf.core.common.Collector;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: chejiangyi
 * @version: 2019-08-01 13:49
 **/
public class ThreadMonitor {
    private  ThreadPoolExecutor threadPoolExecutor;
    private String name;
    //="bsf.threadPool.system";
    public ThreadMonitor(String name,ThreadPoolExecutor threadPoolExecutor){
        this.threadPoolExecutor = threadPoolExecutor;
        this.name = name;
        Collector.Default.call(name+".active.count").set(()->{return threadPoolExecutor==null?0: threadPoolExecutor.getActiveCount();});
        Collector.Default.call(name+".core.poolSize").set(()->{return threadPoolExecutor==null?0:threadPoolExecutor.getCorePoolSize();});
        Collector.Default.call(name+".poolSize.largest").set(()->{return threadPoolExecutor==null?0:threadPoolExecutor.getLargestPoolSize();});
        Collector.Default.call(name+".poolSize.max").set(()->{return threadPoolExecutor==null?0:threadPoolExecutor.getMaximumPoolSize();});
        Collector.Default.call(name+".poolSize.count").set(()->{return threadPoolExecutor==null?0:threadPoolExecutor.getPoolSize();});
        Collector.Default.call(name+".queue.size").set(()->{return threadPoolExecutor==null?0:threadPoolExecutor.getQueue().size();});
        Collector.Default.call(name+".task.count").set(()->{return threadPoolExecutor==null?0:threadPoolExecutor.getTaskCount();});
        Collector.Default.call(name+".task.completed").set(()->{return threadPoolExecutor==null?0:threadPoolExecutor.getCompletedTaskCount();});
    }

    public Collector.Hook hook(){
        return Collector.Default.hook(name+".hook");
    }
}
