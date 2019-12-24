package com.yh.csx.bsf.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * 缓存的Key
     *
     * @return Key
     */
    String key() default "";

    /**
     * 超时时间，单位：秒
     *
     * @return 超时时间
     */
    int timeout();
}
