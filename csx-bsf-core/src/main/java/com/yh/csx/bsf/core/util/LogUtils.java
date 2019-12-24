package com.yh.csx.bsf.core.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author: chejiangyi
 * @version: 2019-07-04 11:39
 **/
@Slf4j
public class LogUtils {


    public static boolean isDebugEnabled(){
        return  PropertyUtils.getSystemProperty("bsf.log.debug.enabled",true);
    }

    public static boolean isInfoEnabled(){
        return PropertyUtils.getSystemProperty("bsf.log.info.enabled",true);
    }

    public static boolean isErrorEnabled(){
        return PropertyUtils.getSystemProperty("bsf.log.error.enabled",true);
    }

    public static boolean isWarnEnabled(){
        return PropertyUtils.getSystemProperty("bsf.log.warn.enabled",true);
    }

    public static void info(Class cls,String project,String msg)
    {
        if(isInfoEnabled()&&log.isInfoEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.info("[BSF][" + project + "]" + msg);
        }
    }
    public static void debug(Class cls,String project,String msg)
    {
        if(isDebugEnabled()&&log.isDebugEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.debug("[BSF][" + project + "]" + msg);
        }
    }
    public static void error(Class cls,String project,String msg,Throwable exp)
    {
        if(isErrorEnabled()&&log.isErrorEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.error("[BSF][" + project + "]"+ msg, exp);
        }
    }

    public static void warn(Class cls,String project,String msg)
    {
        if(isWarnEnabled()&&log.isWarnEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.warn("[BSF][" + project + "]" + msg);
        }
    }
    public static void warn(Class cls,String project,String msg,Throwable exp)
    {
        if(isWarnEnabled()&&log.isWarnEnabled()) {
            org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
            log.warn("[BSF][" + project + "]" + msg,exp);
        }
    }
}
