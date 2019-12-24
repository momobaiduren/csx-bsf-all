package com.yh.csx.bsf.redis.impl;

import com.yh.csx.bsf.core.common.Collector;
import com.yh.csx.bsf.core.util.ExceptionUtils;
import com.yh.csx.bsf.core.util.WarnUtils;
import com.yh.csx.bsf.redis.RedisException;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisClusterMaxAttemptsException;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Huang Zhaoping
 */
public final class RedisLock implements Lock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String UNLOCK_SUCCESS = "1";
    private static final String UNLOCK_SCRIPT = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";

    private String clientId = UUID.randomUUID().toString();
    private JedisCluster jedisCluster;
    private SetParams lockParam;
    private String lockKey;

    public RedisLock(JedisCluster jedisCluster, String key, long timeout, TimeUnit unit) {
        if (key == null || (key = key.trim()).length() == 0) {
            throw new RedisException("Redis锁的Key不能为空");
        }
        int seconds = (int) unit.toSeconds(timeout);
        if (seconds <= 0) {
            throw new RedisException("Redis锁的超时时间必须大于0");
        }
        this.jedisCluster = jedisCluster;
        this.lockKey = key;
        this.lockParam = SetParams.setParams().ex(seconds).nx();

    }


    @Override
    public void lock() {
        try {
            doLock(0L, false);
        } catch (InterruptedException e) {
            throw new RedisException("获取Redis锁被中断");
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        doLock(0L, true);
    }

    @Override
    public boolean tryLock() {
        return doLock();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return doLock(unit.toNanos(time), true);
    }

    @Override
    public void unlock() {
        if (!doUnlock()) {        	
        	RedisException e=new RedisException("Redis分布式锁释放异常，Key：" + lockKey + "，Client：" + clientId);        	
        	String content=ExceptionUtils.trace2String(e);
        	Collector.Default.value("jedis.cluster.lock.error.detail").set(content);
        	WarnUtils.notifynow("ERROR", "Redis分布式锁释放异常", content);
        	throw e;
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("Redis分布式锁不支持newCondition操作");
    }

    private boolean doLock() {
        boolean success = false;
        int retryAttempts = 3;
        do {
            try {
                String result = jedisCluster.set(lockKey, clientId, lockParam);
                success = LOCK_SUCCESS.equalsIgnoreCase(result);
            } catch (JedisClusterMaxAttemptsException e) {
                // retry
            }
        } while (!success && retryAttempts-- > 0);
        return success;
    }

    private boolean doLock(long waitNanos, boolean interruptibly) throws InterruptedException {
        long deadline = waitNanos <= 0 ? Long.MAX_VALUE : System.nanoTime() + waitNanos;
        while (!doLock()) {
            if (deadline > System.nanoTime()) {
                LockSupport.parkNanos(100000000L);// 等100毫秒
                if (interruptibly && Thread.interrupted()) {
                    throw new InterruptedException();
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean doUnlock() {
        List<String> keys = Arrays.asList(lockKey);
        List<String> args = Arrays.asList(clientId);
        Object result = jedisCluster.eval(UNLOCK_SCRIPT, keys, args);
        return UNLOCK_SUCCESS.equalsIgnoreCase(String.valueOf(result));
    }
}
