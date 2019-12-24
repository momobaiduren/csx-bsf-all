package com.yh.csx.bsf.mq.rocketmq;

import com.yh.csx.bsf.core.common.Collector;

/**
 * @author Huang Zhaoping
 */
public class RocketMQMonitor {
    private static String name="rocketmq.info";

    public static Collector.Hook hook(){
        return Collector.Default.hook(name+".hook");
    }
}
