package com.yh.csx.bsf.redis.impl;

import com.yh.csx.bsf.core.serialize.JsonSerializer;
import com.yh.csx.bsf.core.util.JsonUtils;
import com.yh.csx.bsf.redis.RedisException;
import com.yh.csx.bsf.redis.RedisProvider;
import lombok.Getter;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * @author Huang Zhaoping
 */
public class ClusterRedisProvider extends AbstractRedisProvider implements RedisProvider {
    private static final String NULL_VALUE = "<NULL>";

    private JedisCluster jedisCluster;
    @Getter
    private RedisClusterMonitor redisMonitor;

    public ClusterRedisProvider(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
        this.redisMonitor = new RedisClusterMonitor(jedisCluster);
    }

    @Override
    public boolean exists(String key) {
        return this.redisMonitor.hook().run("exists",()->
            jedisCluster.exists(newKey(key))
        );
    }

    @Override
    public boolean delete(String key) {
        return this.redisMonitor.hook().run("delete",()-> {
            Long count = jedisCluster.del(newKey(key));
            return count != null && count > 0;
        });
    }

    @Override
    public boolean expire(String key, int seconds) {
        return this.redisMonitor.hook().run("expire",()-> {
            return toBool(jedisCluster.expire(newKey(key), seconds));
        });
    }

    @Override
    public void set(String key, Object value) {
        this.redisMonitor.hook().run("set",()-> {
            jedisCluster.set(newKey(key), toStr(value));
            return 0;
        });
    }

    @Override
    public void set(String key, Object value, int seconds) {
        this.redisMonitor.hook().run("set",()-> {
            jedisCluster.setex(newKey(key), seconds, toStr(value));
            return 0;
        });
    }

    @Override
    public boolean setIfAbsent(String key, Object value) {
        return this.redisMonitor.hook().run("setIfAbsent",()-> {
            return toBool(jedisCluster.setnx(newKey(key), toStr(value)));
        });
    }

    @Override
    public boolean setIfAbsent(String key, Object value, int seconds) {
        return this.redisMonitor.hook().run("setIfAbsent",()-> {
            SetParams params = SetParams.setParams().nx().ex(seconds);
            return toBool(jedisCluster.set(newKey(key), toStr(value), params));
        });
    }

    @Override
    public String get(String key) {
        return this.redisMonitor.hook().run("get",()-> {
            return jedisCluster.get(newKey(key));
        });
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        return this.redisMonitor.hook().run("get",()-> {
            return toObj(jedisCluster.get(newKey(key)), type);
        });
    }

    @Override
    public <T> List<T> getList(String key, Class<T> type) {
        return this.redisMonitor.hook().run("getList",()->{
            return toObj(jedisCluster.get(newKey(key)), new ParameterizedType() {
                public String getTypeName() {
                    return List.class.getTypeName();
                }

                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                public Type getRawType() {
                    return List.class;
                }

                public Type getOwnerType() {
                    return null;
                }
            });
        });
    }

    @Override
    public void set(String key, String field, Object value) {
        this.redisMonitor.hook().run("set",()-> {
            jedisCluster.hset(newKey(key), field, toStr(value));
            return 0;
        });
    }

    @Override
    public <T> T get(String key, String field, Class<T> type) {
        return this.redisMonitor.hook().run("get",()-> {
            return toObj(jedisCluster.hget(newKey(key), field), type);
        });
    }

    @Override
    public void set(String key, Map<String, Object> values) {
        this.redisMonitor.hook().run("set",()-> {
            Map<String, String> strMap = new HashMap<>();
            if (values != null) {
                values.forEach((field, value) -> strMap.put(field, toStr(value)));
            }
            jedisCluster.hmset(newKey(key), strMap);
            return 0;
        });
    }

    @Override
    public <T> List<T> get(String key, String[] fields, Class<T> type) {
        return this.redisMonitor.hook().run("get",()-> {
            if (fields == null || fields.length == 0) return Collections.emptyList();
            List<String> list = jedisCluster.hmget(newKey(key), fields);
            if (list == null) return Collections.emptyList();
            if (type == String.class) {
                return (List<T>) list;
            } else {
                return (List<T>) list.stream().filter(v -> v != null).map(s -> toObj(s, type)).collect(Collectors.toList());
            }
        });
    }

    @Override
    public <T> List<T> get(String key, Collection<String> fields, Class<T> type) {
        return this.redisMonitor.hook().run("get",()-> {
            if (fields == null || fields.size() == 0) return Collections.emptyList();
            return get(key, fields.toArray(new String[fields.size()]), type);
        });
    }

    @Override
    public Long increment(String key, Long step) {
        return this.redisMonitor.hook().run("increment",()->{return jedisCluster.incrBy(newKey(key), step);});
    }

    @Override
    public <T> T cache(String key, int timeout, Callable<T> callable, Type type) {
        return this.redisMonitor.hook().run("cache",()-> {
            String json = get(key);
            if (json != null) {
                if (NULL_VALUE.equals(json)) {
                    return null;
                }
                return JsonUtils.deserialize(json, type);
            }
            if (timeout <= 0) {
                throw new RedisException("超时时间必须大于0");
            }
            T value;
            try {
                value = callable.call();
            } catch (Exception e) {
                if (e instanceof RedisException) {
                    throw (RedisException) e;
                } else {
                    throw new RedisException(e);
                }
            }
            if (value != null) {
                json = new JsonSerializer().serialize(value);
            } else {
                json = NULL_VALUE;
            }
            set(key, json, timeout);
            return value;
        });
    }

    @Override
    public Lock getLock(String key, int timeout) {
        return this.redisMonitor.hook().run("getLock",()->{
            return new RedisLock(jedisCluster, newKey(key), timeout, TimeUnit.SECONDS);
        });
    }

    @Override
    public boolean tryLock(String key, int timeout, Runnable runnable) {
        return this.redisMonitor.hook().run("tryLock",()->{
            Lock lock = getLock(key, timeout);
            if (lock.tryLock()) {
                try {
                    runnable.run();
                    return true;
                } finally {
                    lock.unlock();
                }
            } else {
                return false;
            }
        });
    }

    @Override
    public void lock(String key, int timeout, Runnable runnable) {
         this.redisMonitor.hook().run("lock",()->{
            Lock lock = getLock(key, timeout);
            lock.lock();
            try {
                runnable.run();
            } finally {
                lock.unlock();
            }
            return 0;
        });
    }

    @Override
    public <T> T tryLock(String key, int timeout, Callable<T> callable) {
        return this.redisMonitor.hook().run("tryLock",()-> {
            Lock lock = getLock(key, timeout);
            if (lock.tryLock()) {
                try {
                    return doCallable(callable);
                } finally {
                    lock.unlock();
                }
            } else {
                return null;
            }
        });
    }

    @Override
    public <T> T lock(String key, int timeout, Callable<T> callable) {
        return this.redisMonitor.hook().run("lock",()-> {
            Lock lock = getLock(key, timeout);
            lock.lock();
            try {
                return doCallable(callable);
            } finally {
                lock.unlock();
            }
        });
    }

    private <T> T doCallable(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RedisException("Call failed in redis lock", e);
            }
        }
    }

}
