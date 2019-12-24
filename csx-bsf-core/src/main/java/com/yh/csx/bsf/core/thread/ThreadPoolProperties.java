package com.yh.csx.bsf.core.thread;

import com.yh.csx.bsf.core.util.PropertyUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: chejiangyi
 * @version: 2019-07-23 21:54
 **/
@ConfigurationProperties
public class ThreadPoolProperties {
    public static String Prefix="bsf.threadpool.";
    public static int getThreadPoolMaxSize() {
        return PropertyUtils.getPropertyCache("bsf.threadpool.max",500);
    }
    public static int getThreadPoolMinSize() {
        return PropertyUtils.getPropertyCache("bsf.threadpool.min",0);
    }
}
