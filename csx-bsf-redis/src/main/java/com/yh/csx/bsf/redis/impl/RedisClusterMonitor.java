package com.yh.csx.bsf.redis.impl;

import com.yh.csx.bsf.core.common.Collector;
import com.yh.csx.bsf.core.util.ReflectionUtils;
import lombok.val;
import org.apache.commons.pool2.impl.GenericObjectPool;
import redis.clients.jedis.JedisCluster;

/**
 * @author: chejiangyi
 * @version: 2019-08-03 16:06
 **/
public class RedisClusterMonitor {
    private JedisCluster jedisCluster;
    private String name="jedis.cluster";
    public RedisClusterMonitor(JedisCluster jedisCluster){
        this.jedisCluster = jedisCluster;
        }

    public void collect(){
        Integer wait=0;Integer active=0; Integer idle=0;
        StringBuilder sb = new StringBuilder();
        for(val e:this.jedisCluster.getClusterNodes().entrySet()){
            val pool = e.getValue();
            val innerPool = (GenericObjectPool)ReflectionUtils.getFieldValue(pool,"internalPool");
            wait+=innerPool.getNumWaiters();
            active+=innerPool.getNumActive();
            idle += innerPool.getNumIdle();
            sb.append(String.format("[连接池:%s]\r\n",e.getKey()));
            sb.append(String.format("排队等待的请求数:%s,",innerPool.getNumWaiters()));
            sb.append(String.format("活跃使用中的请求数:%s,",innerPool.getNumActive()));
            sb.append(String.format("空闲的请求数:%s\r\n",innerPool.getNumIdle()));
            sb.append(String.format("最小空闲请求数:%s,",innerPool.getMinIdle()));
            sb.append(String.format("最大空闲请求数:%s,",innerPool.getMaxIdle()));
            sb.append(String.format("最大连接数:%s\r\n",innerPool.getMaxTotal()));
            sb.append(String.format("租借的连接数:%s,",innerPool.getBorrowedCount()));
            sb.append(String.format("创建的连接数:%s,",innerPool.getCreatedCount()));
            sb.append(String.format("返还的连接数:%s,",innerPool.getReturnedCount()));
            sb.append(String.format("销毁的连接数:%s\r\n",innerPool.getDestroyedCount()));
            sb.append(String.format("是否开启租用时回收超时连接:%s,",innerPool.getRemoveAbandonedOnBorrow()));
            sb.append(String.format("是否开启维护时回收超时连接:%s,",innerPool.getRemoveAbandonedOnMaintenance()));
            sb.append(String.format("是否在回收超时连接时打印日志:%s,",innerPool.getLogAbandoned()));
            sb.append(String.format("回收超时连接时间(秒):%s\r\n",innerPool.getRemoveAbandonedTimeout()));
        }
        Collector.Default.value(name+".pool.detail").set(sb.toString());
        Collector.Default.value(name+".pool.wait").set(wait);
        Collector.Default.value(name+".pool.active").set(active);
        Collector.Default.value(name+".pool.idle").set(idle);
    }

    public Collector.Hook hook(){
        return Collector.Default.hook(name+".hook");
    }
}
