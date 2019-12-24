package com.yh.csx.bsf.core.base;

import java.util.HashMap;

/**
 * @author: chejiangyi
 * @version: 2019-08-10 16:00
 **/
public enum BsfEventEnum {
    PropertyCacheUpdateEvent(new HashMap<String,Object>().getClass(),"属性缓存更新事件");

    String desc;
    Class dataClass;
    public Class getDataClass(){
        return dataClass;
    }
    BsfEventEnum(Class dataClass,String desc){
        this.desc = desc;
        this.dataClass = dataClass;
    }
}
