package com.yh.csx.bsf.demo.redis;

import com.alibaba.fastjson.TypeReference;
import com.yh.csx.bsf.redis.RedisProvider;
import com.yh.csx.bsf.redis.annotation.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Huang Zhaoping
 */
@RestController
@SpringBootApplication
public class RedisApplication {

    @Autowired(required = false)
    private RedisProvider redisProvider;

    @GetMapping("/get")
    public String get(String key) {
        return redisProvider.get(key);
    }

    @GetMapping("/set")
    public void set(String key, String value, int timeout) {
        redisProvider.set(key, value, timeout);
    }

    @GetMapping("/call")
    @RedisCache(timeout = 60)
    public String call() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @GetMapping("/callVoid")
    @RedisCache(timeout = 60)
    public void callVoid() {
        System.out.println("call");
    }

    @GetMapping("/callList")
    @RedisCache(timeout = 60)
    public List<String> callList() {
        return Arrays.asList("1", "2", String.valueOf(Math.random()));
    }

    @GetMapping("/callArray")
    @RedisCache(timeout = 60)
    public String[] callArray(int length) {
        System.out.println("call");
        if (length != 10) {
            throw new IllegalArgumentException("length != 10");
        }
        return new String[length];
    }

    @GetMapping("/callComplex")
    @RedisCache(key = "'user-'+#userId", timeout = 60)
    public User callComplex(long userId) {
        return new User(userId, "hello: " + System.currentTimeMillis(), Arrays.asList(new User.Job(userId + "001", "haha")));
    }

    @GetMapping("/callComplexList")
    @RedisCache(key = "'list-user-'+#userId", timeout = 60)
    public List<User> callComplexList(long userId) {
        return Arrays.asList(new User(userId, "hello: " + System.currentTimeMillis(), Arrays.asList(new User.Job(userId + "001", "haha"))));
    }

    @GetMapping("/callComplexMap")
    @RedisCache(key = "'map-user-'+#userId", timeout = 60)
    public Map<Integer, User> callComplexMap(long userId) {
        Map<Integer, User> values = new HashMap<>();
        values.put((int) userId, new User(userId, "hello: " + userId, Arrays.asList(new User.Job(userId + "001", "haha"))));
        return values;
    }

    @GetMapping("/callback")
    public List<User> callback(long userId) {
        List<User> list = redisProvider.cache("user-" + userId, 60, () -> Arrays.asList(new User(userId, "hello: " + userId, Arrays.asList(new User.Job(userId + "001", "haha")))), new TypeReference<List<User>>() {
        }.getType());
        System.out.println(list);
        return list;
    }

    @GetMapping("/callbackMap")
    public Map<Integer, User> callbackMap(long userId) {
        Map<Integer, User> map = redisProvider.cache("user-" + userId, 60, () -> {
            Map<Integer, User> values = new HashMap<>();
            values.put((int) userId, new User(userId, "hello: " + userId, Arrays.asList(new User.Job(userId + "001", "haha"))));
            return values;
        }, new TypeReference<Map<Integer, User>>() {
        }.getType());
        return map;
    }

    @GetMapping("/testHm")
    public List<User> testHm() {
        return redisProvider.get("hello", Arrays.asList("11", "22"), User.class);
    }

    @GetMapping("/callBiz")
    public User callBiz(long userId) throws Exception {
        if (userId != 10) {
            throw new IllegalArgumentException("你大爷的错误了");
        }
        return new User(userId, "hello: " + userId, Arrays.asList(new User.Job(userId + "001", null)));
    }

    @GetMapping("/callError")
    public User callError(long userId) throws Exception {
        if (userId != 10) {
            throw new IllegalArgumentException("你大爷的错误了");
        }
        return new User(userId, "hello: " + userId, Arrays.asList(new User.Job(userId + "001", null)));
    }

    @GetMapping("/getList")
    public List<User> getList() throws Exception {
        List<User> list = Arrays.asList(new User(1L, "hello: 1", Arrays.asList(new User.Job("001", null))),
                new User(2L, "hello: 2", Arrays.asList(new User.Job("002", null))));
        redisProvider.set("getList", list);
        list = redisProvider.getList("getList", User.class);
        System.out.println(list);
        return list;
    }

    @GetMapping("/lock")
    public List<User> lock(String key, int timeout, int type, int sleep) throws Exception {
        List<User> list = Arrays.asList(new User(1L, "hello: 1", Arrays.asList(new User.Job("001", null))),
                new User(2L, "hello: 2", Arrays.asList(new User.Job("002", null))));
        Callable<List<User>> callable = () -> {
            System.out.println("start run... ");
            Thread.sleep(sleep * 1000);
            System.out.println("run finished...");
            return list;
        };
        if (type == 1) {
            redisProvider.lock(key, timeout, () -> {
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else if (type == 2) {
            boolean result = redisProvider.tryLock(key, timeout, () -> {
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            System.out.println(result);
        } else if (type == 3) {
            return redisProvider.lock(key, timeout, callable);
        } else if (type == 4) {
            return redisProvider.tryLock(key, timeout, callable);
        }
        return null;
    }

    @GetMapping("/testLock")
    public String testBatch(int threads, int times) {
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch count = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            final String key = "lock-" + (i % 10);
            service.submit(() -> {
                for (int j = 0; j < times; j++) {
                    redisProvider.lock(key, 60, () -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    System.out.println(j);
                }
                count.countDown();
            });
        }
        try {
            count.await();
        } catch (InterruptedException e) {
        }
        return "done";
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
