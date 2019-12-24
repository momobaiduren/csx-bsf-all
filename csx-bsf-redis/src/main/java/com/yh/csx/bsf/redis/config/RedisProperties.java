package com.yh.csx.bsf.redis.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * @author Huang Zhaoping
 */
@ConfigurationProperties(prefix = "bsf.redis")
public class RedisProperties {

    public static String Project="Redis";
    public static String SpringApplicationName="spring.application.name";
    public static String BsfRedisNodes="bsf.redis.nodes";

    private List<String> nodes;
    @Getter @Setter
    private int connectTimeout = 5000;
    @Getter @Setter
    private int soTimeout = 60000;
    @Getter @Setter
    private int maxAttempts = 5;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String keyPrefix;
    @Getter @Setter
    private String clientName;
    @Getter @Setter
    private long maxWaitMillis = -1L;
    @Getter @Setter
    private boolean testOnCreate=false;
    @Getter @Setter
    private boolean testOnBorrow=false;
    @Getter @Setter
    private boolean testOnReturn=false;
    @Getter @Setter
    private boolean testWhileIdle=true;

    @Getter @Setter
    private int maxTotal = 50;
    @Getter @Setter
    private int maxIdle = 20;
    @Getter @Setter
    private int minIdle = 0;

    @NestedConfigurationProperty
    private GenericObjectPoolConfig pool;

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }


    public GenericObjectPoolConfig getPool() {
        if (pool == null) {
            pool = new GenericObjectPoolConfig();
            pool.setMaxIdle(this.maxIdle);
            pool.setMaxTotal(this.maxTotal);
            pool.setMinIdle(this.minIdle);
            pool.setMaxWaitMillis(this.maxWaitMillis);
            pool.setTestOnCreate(this.testOnCreate);
            pool.setTestOnBorrow(this.testOnBorrow);
            pool.setTestOnReturn(this.testOnReturn);
            pool.setTestWhileIdle(this.testWhileIdle);
        }
        return pool;
    }

    public void setPool(GenericObjectPoolConfig pool) {
        this.pool = pool;
    }
}
