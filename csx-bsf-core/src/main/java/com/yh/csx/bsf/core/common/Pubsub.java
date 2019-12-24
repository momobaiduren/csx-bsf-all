package com.yh.csx.bsf.core.common;

import com.yh.csx.bsf.core.base.BsfEventEnum;
import com.yh.csx.bsf.core.base.BsfException;
import com.yh.csx.bsf.core.base.Callable;
import com.yh.csx.bsf.core.config.CoreProperties;
import com.yh.csx.bsf.core.util.LogUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: chejiangyi
 * @version: 2019-08-10 13:44
 * 发布订阅
 **/
public class Pubsub {
    public static Pubsub Default=new Pubsub();
    private Map<String,ConcurrentHashMap<String,Sub>> subscribeList= new ConcurrentHashMap<>();
    private Object lock = new Object();
    public <T> void pub(String event,T data){
        val subs = subscribeList.get(event);
        if(subs!=null){
           for(val sub:subs.entrySet()){
               try {
                   sub.getValue().action.invoke(data);
               } catch (Exception e) {
                   LogUtils.error(Pubsub.class, CoreProperties.Project,"分发订阅失败",e);
               }
           }
        }
    }
    private  <T> void sub(String event,Sub<T> action){
        if(!subscribeList.containsKey(event)){
            synchronized (lock){
                if(!subscribeList.containsKey(event)) {
                    subscribeList.putIfAbsent(event, new ConcurrentHashMap());
                }
            }
        }
        subscribeList.get(event).putIfAbsent(action.name,action);
    }

    public <T> void sub(BsfEventEnum event,Sub<T> action){
        sub(event.toString(),action);
    }

    public boolean removeSub(String event,String subName){
        val subs = subscribeList.get(event);
        if(subs!=null)
        {
            subs.remove(subName);
            if(subs.size()==0){
                subscribeList.remove(event);
            }
            return true;
        }
        return false;
    }
    @Data
    @AllArgsConstructor
    public static class Sub<T>{
        private String name;
        private Callable.Action1<T> action;
    }
}
