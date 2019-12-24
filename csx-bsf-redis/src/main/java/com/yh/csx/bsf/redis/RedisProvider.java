package com.yh.csx.bsf.redis;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

/**
 * @author Huang Zhaoping
 */
public interface RedisProvider {

    /**
     * 判断Key是否存在
     *
     * @param key 缓存Key
     * @return true：存在，false：不存在
     */
    boolean exists(String key);

    /**
     * 删除Key对应的记录
     *
     * @param key 缓存Key
     * @return true：删除，false：删除失败
     */
    boolean delete(String key);

    /**
     * 设置key在指定秒后过期
     *
     * @param key 缓存Key
     * @param seconds 缓存秒数
     * @return true：操作成功，false：操作失败
     */
    boolean expire(String key, int seconds);

    /**
     * 设置Key对应的值
     *
     * @param key 缓存Key
     * @param value 缓存值
     */
    void set(String key, Object value);

    /**
     * 设置Key对应的值，并设置指定秒后过期
     *
     * @param key 缓存Key
     * @param value 缓存值
     * @param seconds 缓存秒数
     */
    void set(String key, Object value, int seconds);

    /**
     * 当Key不存在时设置值
     *
     * @param key 缓存Key
     * @param value 缓存值
     * @return true：设置成功，false：设置失败
     */
    boolean setIfAbsent(String key, Object value);

    /**
     * 当Key不存在时设置值，并指定秒后过期
     *
     * @param key 缓存Key
     * @param value 缓存值
     * @param seconds 缓存秒数
     * @return true：设置成功，false：设置失败
     */
    boolean setIfAbsent(String key, Object value, int seconds);

    /**
     * 获取Key对应的原始值
     *
     * @param key 缓存Key
     * @return 返回字符串值
     */
    String get(String key);

    /**
     * 获取Key的值，并转换成指定的类型
     *
     * @param key 缓存Key
     * @param type 返回值类型
     * @param <T> 对象类型
     * @return 返回指定类型对象
     */
    <T> T get(String key, Class<T> type);

    /**
     * 获取Key的值，并转换成指定的类型列表
     *
     * @param key 缓存Key
     * @param type 返回值类型
     * @param <T> 对象类型
     * @return 返回指定类型对象
     */
    <T> List<T> getList(String key, Class<T> type);

    /**
     * 设置Key下字段的值
     *
     * @param key 缓存Key
     * @param field Hash表的字段
     * @param value Hash表的值
     */
    void set(String key, String field, Object value);

    /**
     * 获取Key下字段的值，并转换成指定的类型
     *
     * @param key 缓存Key
     * @param field Hash表的字段
     * @param type 返回值类型
     * @param <T> 对象类型
     * @return 返回指定类型对象
     */
    <T> T get(String key, String field, Class<T> type);

    /**
     * 设置Key下多个字段的值
     *
     * @param key 缓存Key
     * @param values 多个字段的值
     */
    void set(String key, Map<String, Object> values);

    /**
     * 获取Key下多个字段的值
     *
     * @param key 缓存Key
     * @param fields 字段数组
     * @param type 返回值类型
     * @param <T> 对象类型
     * @return 返回指定类型对象列表
     */
    <T> List<T> get(String key, String[] fields, Class<T> type);

    /**
     * 获取Key下多个字段的值
     *
     * @param key 缓存Key
     * @param fields 字段列表
     * @param type 返回值类型
     * @param <T>对象类型
     * @return 返回指定类型对象列表
     */
    <T> List<T> get(String key, Collection<String> fields, Class<T> type);

    /**
     * 对某个Key进行自增
     *
     * @param key 缓存Key
     * @param step 步进值
     * @return 返回自增后的值
     */
    Long increment(String key, Long step);

    /**
     * 根据指定Key获取缓存数据，若不存在，则调用指定方法获取，并缓存结果
     *
     * @param key 缓存Key
     * @param timeout 超时时间，单位：秒
     * @param callable 回调方法
     * @param type 结果类型
     * @param <T> 值类型
     * @return 指定类型值
     */
    <T> T cache(String key, int timeout, Callable<T> callable, Type type);

    /**
     * 获取Redis锁，使用方式与ReentrantLock相同，但是不支持newCondition操作。
     *
     * @param key 锁的Key
     * @param timeout 锁的超时时间，单位：秒
     * @return 返回redis锁对象
     */
    Lock getLock(String key, int timeout);

    /**
     * 尝试获取锁，锁失败，直接返回false，锁成功并且执行完成，返回true
     *
     * @param key 锁的Key
     * @param timeout 锁的超时时间，单位：秒
     * @param runnable 回调方法
     * @return true：锁成功并且执行完成，false：锁失败
     */
    boolean tryLock(String key, int timeout, Runnable runnable);

    /**
     * 根据Key获取锁，并执行操作
     *
     * @param key 锁的Key
     * @param timeout 锁的超时时间，单位：秒
     * @param runnable 回调方法
     */
    void lock(String key, int timeout, Runnable runnable);

    /**
     * 尝试获取锁，锁失败，直接返回null，锁成功并且执行完成，返回回调结果
     *
     * @param key 锁的Key
     * @param timeout 锁的超时时间，单位：秒
     * @param callable 回调方法
     * @return 回调方法结果，锁失败时返回null
     */
    <T> T tryLock(String key, int timeout, Callable<T> callable);

    /**
     * 根据Key获取锁，并执行操作
     *
     * @param key 锁的Key
     * @param timeout 锁的超时时间，单位：秒
     * @param callable 回调方法
     * @return 回调方法结果
     */
    <T> T lock(String key, int timeout, Callable<T> callable);

}
