package com.yh.csx.bsf.core.util;

import com.yh.csx.bsf.core.base.BsfEnvironmentEnum;
import com.yh.csx.bsf.core.base.Callable;
import com.yh.csx.bsf.core.base.Environment;
import com.yh.csx.bsf.core.common.PropertyCache;
import com.yh.csx.bsf.core.initializer.CoreApplicationContextInitializer;
import lombok.val;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.convert.ApplicationConversionService;

/**
 * @author: chejiangyi
 * @version: 2019-06-15 10:04
 **/
public class PropertyUtils {
    public static String NULL="<?NULL?>";
    public static void eachProperty(Callable.Action3<String,String,Object> call){
        for(val key: System.getProperties().stringPropertyNames()){
            call.invoke("properties",key,System.getProperty(key));
        }
        for(val kv: System.getenv().entrySet()){
            call.invoke("env",kv.getKey(),kv.getValue());
        }
    }

    private static <T> T getProperty(String key,T defaultvalue) {
        String value = System.getProperty(key);
        if(value == null){
            value = System.getenv(key);
        }
        if(value == null && ContextUtils.getApplicationContext()!=null)
        {
            value = ContextUtils.getApplicationContext().getEnvironment().getProperty(key);
        }
        if(value == null)
        {
            return defaultvalue;
        }
        return (T) ConvertUtils.convert(value,defaultvalue.getClass());
    }

    public static Object getProperty(String key) {
        String value = System.getProperty(key);
        if(value == null){
            value = System.getenv(key);
        }
        if(value == null && ContextUtils.getApplicationContext()!=null)
        {
            value = ContextUtils.getApplicationContext().getEnvironment().getProperty(key);
        }
        return value;
    }

    public static <T> T getEnvProperty(String key,T defaultvalue){
        String value = System.getenv(key);
        if(value==null)
        {    return defaultvalue;}
        else
        {    return  (T) ConvertUtils.convert(value,defaultvalue.getClass());}
    }


    public static <T> T getSystemProperty(String key,T defaultvalue)
    {
        String value = System.getProperty(key);
        if(value==null)
        {    return defaultvalue;}
        else
        {    return  (T) ConvertUtils.convert(value,defaultvalue.getClass());}
    }

    public static void setDefaultInitProperty(Class cls,String module,String key,String propertyValue)
    {
        setDefaultInitProperty(cls,module,key,propertyValue,"");
    }

    public static void setDefaultInitProperty(Class cls,String module,String key,String propertyValue,String message){
        if(Strings.isEmpty(PropertyUtils.getPropertyCache(key,""))) {
            if (!Strings.isEmpty(propertyValue)) {
                System.setProperty(key, propertyValue);
                PropertyCache.Default.tryUpdateCache(key,propertyValue);
                LogUtils.info(cls, module, "设置" + key + "=" + propertyValue+" "+message);
            }
        }
    }

    public static <T> T getPropertyCache(String key,T defaultvalue){
        return PropertyCache.Default.get(key,defaultvalue);
    }

}
