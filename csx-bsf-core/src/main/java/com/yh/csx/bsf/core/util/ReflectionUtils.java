package com.yh.csx.bsf.core.util;

import com.yh.csx.bsf.core.base.BsfException;
import lombok.val;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: chejiangyi
 * @version: 2019-07-31 09:54
 **/
public class ReflectionUtils {
    public static Class classForName(String type){
        try {
            return Class.forName(type);
        }catch (Exception exp){
            throw new BsfException(exp);
        }
    }
    public static Class tryClassForName(String type){
        try {
            return Class.forName(type);
        }catch (Exception exp){
            return null;
        }
    }
    public static Method findMethod(Class cls,String methodName){
        Method find = null;
        while (cls!=null) {
            for (val methods : new Method[][]{cls.getMethods(), cls.getClass().getDeclaredMethods()}) {
                for (val method : methods) {
                    if (method.getName().equalsIgnoreCase(methodName)) {
                        find = method;
                        break;
                    }
                }
            }
            cls=cls.getSuperclass();
        }
        return find;
    }
    public static Method findMethod0(Class cls,String methodName,Class... argsTypes) throws NoSuchMethodException, SecurityException{
        Method find = null;        
        if (cls!=null) {
        	find= cls.getMethod(methodName, argsTypes);
        }
        return find;
    }
    public static <T> T tryCallMethod(Object obj, String methodName,Object[] param,T defaultValue){
        try {
            if(obj !=null) {
                val find = findMethod(obj.getClass(),methodName);
                if (find != null) {
                    if (!find.isAccessible()) {
                        find.setAccessible(true);
                    }
                    return (T) find.invoke(obj,param);
                }
            }
            return defaultValue;
        }
        catch (Exception exp){
            return defaultValue;
        }
    }

    public static Object callMethod(Object obj, String methodName,Object[] param){
        try {
            val find = findMethod(obj.getClass(), methodName);
            if (find != null) {
                return find.invoke(obj,param);
            }
            throw new Exception("未找到方法"+StringUtils.nullToEmpty(methodName));
        }catch (Exception exp){
            throw new BsfException(exp);
        }
    }

    public static Object callMethod(Class<?> clazz, String methodName, Object[] param){
        try {
            val find = findMethod(clazz, methodName);
            if (find != null) {
                return find.invoke(null, param);
            }
            throw new Exception("未找到方法"+StringUtils.nullToEmpty(methodName));
        }catch (Exception exp){
            throw new BsfException(exp);
        }
    }
    public static Object callMethodWithParams(Class<?> clazz, String methodName, Object[] params,Class<?>... paramTypes){
        try {
            val find = findMethod0(clazz, methodName,paramTypes);
            if (find != null) {
                return find.invoke(null, params);
            }
            throw new Exception("未找到方法"+StringUtils.nullToEmpty(methodName));
        }catch (Exception exp){
            throw new BsfException(exp);
        }
    }
    public static Object callMethodWithParams(Object object, String methodName, Object[] params,Class<?>... paramTypes){
        try {
            val find = findMethod0(object.getClass(), methodName,paramTypes);
            if (find != null) {
                return find.invoke(object, params);
            }
            throw new Exception("未找到方法"+StringUtils.nullToEmpty(methodName));
        }catch (Exception exp){
            throw new BsfException(exp);
        }
    }

    public static Field findField(Class cls,String name){
        Field find = null;
        while (cls!=null) {
            for (val fields : new Field[][]{cls.getFields(), cls.getDeclaredFields()}) {
                for (val field : fields) {
                    if (field.getName().equalsIgnoreCase(name)) {
                        find = field;
                        return find;
                        //break;
                    }
                }
            }
            cls=cls.getSuperclass();
        }
        return find;
    }

    public static <T> T getFieldValue(Object obj,String name){
        try {
            val find = findField(obj.getClass(), name);
            if (find != null) {
                if (!find.isAccessible()) {
                    find.setAccessible(true);
                }
                return (T) find.get(obj);
            }
            throw new Exception("未找到字段" + StringUtils.nullToEmpty(name));
        }catch (Exception e){
            throw new BsfException(e);
        }
    }

    public static <T> T tryGetFieldValue(Object obj,String name,T defaultValue){
        try {
            if(obj !=null) {
                val find = findField(obj.getClass(),name);
                if (find != null) {
                    if(!find.isAccessible()){find.setAccessible(true);}
                    return (T) find.get(obj);
                }
            }
            return defaultValue;
        }
        catch (Exception exp){
            return defaultValue;
        }
    }

    public static <T> T tryGetStaticFieldValue(String cls,String name,T defaultValue) {
        try {
            return tryGetStaticFieldValue(Class.forName(cls),name,defaultValue);
        }
        catch (Exception exp){
            return defaultValue;
        }
    }
    

    public static <T> T tryGetStaticFieldValue(Class cls,String name,T defaultValue){
        try {
            if(cls!=null) {
                val find = findField(cls, name);
                if (find != null) {
                    if (!find.isAccessible()) {
                        find.setAccessible(true);
                    }
                    return (T) find.get(cls);
                }
            }
            return defaultValue;
        }
        catch (Exception exp){
            return defaultValue;
        }
    }

    public static void setFieldValue(Field field,Object obj,Object value){
        try {
            if(!field.isAccessible()){field.setAccessible(true);}
            field.set(obj,value);
        }catch (Exception exp){
            throw  new BsfException(exp);
        }
    }

    public static <T> T tryGetValue(Object obj, String path, T deft) {
        if (obj == null || path == null || path.length() == 0) return deft;
        Object object = obj;
        for(String name : path.split("\\.")){
            if (object == null) break;
            Object value = tryGetFieldValue(object, name, null);
            if (value == null) {
                object = tryCallMethod(object, name, null, null);
            } else {
                object = value;
            }
        }
        return object == null ? deft : (T) object;
    }

    public static <T> T tryGetValue(Object obj, String path) {
        return tryGetValue(obj, path, null);
    }
}
