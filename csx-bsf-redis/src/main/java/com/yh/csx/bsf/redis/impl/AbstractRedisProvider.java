package com.yh.csx.bsf.redis.impl;

import com.yh.csx.bsf.core.serialize.JsonSerializer;
import com.yh.csx.bsf.core.util.JsonUtils;
import com.yh.csx.bsf.redis.RedisException;
import com.yh.csx.bsf.redis.RedisProvider;

import java.lang.reflect.Type;

/**
 * @author Huang Zhaoping
 */
public abstract class AbstractRedisProvider implements RedisProvider {

    private String keyPrefix;

    public void setKeyPrefix(String keyPrefix) {
        if (keyPrefix != null && (keyPrefix = keyPrefix.trim()).length() > 0) {
            this.keyPrefix = keyPrefix.endsWith(":") ? keyPrefix : (keyPrefix + ":");
        } else {
            this.keyPrefix = null;
        }
    }

    protected String newKey(String key) {
        return keyPrefix != null ? (keyPrefix + key) : key;
    }

    protected boolean toBool(Long result) {
        return result != null && result > 0;
    }

    protected boolean toBool(String result) {
        return "OK".equalsIgnoreCase(result);
    }

    protected String toStr(Object obj) {
        if (obj == null) {return null;}
        if (obj instanceof String) {return (String) obj;}
        try {
            return new JsonSerializer().serialize(obj);
        } catch (Exception e) {
            throw new RedisException("对象序列化成JSON失败：" + obj, e);
        }
    }

    protected <T> T toObj(String str, Type type) {
        if (str == null) {return null;}
        if (type == String.class) {return (T) str;}
        try {
            return JsonUtils.deserialize(str, type);
        } catch (Exception e) {
            throw new RedisException("JSON反序列化对象失败：" + str, e);
        }
    }

}
