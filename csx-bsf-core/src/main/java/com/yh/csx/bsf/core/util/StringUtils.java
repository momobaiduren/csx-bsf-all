package com.yh.csx.bsf.core.util;

/**
 * @author: chejiangyi
 * @version: 2019-07-24 11:00
 **/
public class StringUtils {
    public static String nullToEmpty(Object str) {
        return str != null ? str.toString() : "";
    }
    public static boolean isEmpty(String str){
        if(str ==null || str.isEmpty())
        {return true;}
        return false;
    }

    /**
     * 部分字符串获取
     */
    public static String subString2(String str, int maxlen)
    {
        if (org.springframework.util.StringUtils.isEmpty(str))
        { return str;}
        if (str.length() <= maxlen)
        {    return str;}
        return str.substring(0,maxlen);
    }

    /**
     * 部分字符串获取 超出部分末尾...
     */
    public static String subString3(String str, int maxlen)
    {
        if (org.springframework.util.StringUtils.isEmpty(str))
        { return str;}
        if (str.length() <= maxlen)
        {    return str;}
        return str.substring(0,maxlen)+"...";
    }
}
