package com.yh.csx.bsf.shardingjdbc;

import com.yh.csx.bsf.core.base.Callable;
import org.apache.shardingsphere.api.hint.HintManager;

/**
 * @author: chejiangyi
 * @version: 2019-09-01 14:16
 **/
public class ShardingJdbcUtils {
    public static <T> T hitMasterOnly(Callable.Func0<T> action){
        try(HintManager hintManager=HintManager.getInstance()){
            hintManager.setMasterRouteOnly();
            return action.invoke();
        }
    }

    public static <T> T hitSlaveOnly(Comparable<?> slave,Callable.Func0<T> action){
        return hitDataSource(slave,action);
    }

    public static <T> T hitDataSource(Comparable<?> name,Callable.Func0<T> action){
        try(HintManager hintManager=HintManager.getInstance()){
            hintManager.setDatabaseShardingValue(name);
            return action.invoke();
        }
    }
}
