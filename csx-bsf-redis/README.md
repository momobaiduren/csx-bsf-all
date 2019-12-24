# Redis缓存服务

## 介绍
本系统集成了 Cluster Redis，用于同一封装及优化客户端参数，提供常用工具服务类，简化业务端使用。

关于Redis的集群搭建及使用，请详见[官方](https://redis.io/)


## 依赖引用
```java 
<dependency>
	<artifactId>csx-bsf-redis</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>
```
## 配置说明

```shell
##BSF采用了Redis Cluster集群，配置如下
#redis启用开关
bsf.redis.enabled = true			
bsf.redis.connectTimeout = 5000
bsf.redis.soTimeout = 60000
bsf.redis.maxAttempts = 5
#密码设置
bsf.redis.password=		
#键值前缀			
bsf.redis.keyPrefix=				
bsf.redis.clientName=		
#服务器集群地址{ip}:{port}，逗号隔开；必填	
bsf.redis.nodes= 
#redis连接池设置					
bsf.redis.pool.maxTotal = 30		
bsf.redis.pool.maxIdle = 30
bsf.redis.pool.minIdle = 0
bsf.redis.pool.testOnBorrow = false
bsf.redis.pool.testWhileIdle = true
bsf.redis.pool.testOnReturn = false
bsf.redis.pool.testOnCreate = false
bsf.redis.pool.timeBetweenEvictionRunsMillis = 30000
bsf.redis.pool.minEvictableIdleTimeMillis = 60000
    
```


## 代码示例

```java
	@Autowired
	private RedisProvider redisProvider;
	private int timer = 20;

	/**
	* 获取值
	*/
	public String get(String key) {
		return redisProvider.get(key);
	}

	/**
	* 设置键值
	*/
	public void set(String key, String value, int timeout) {
		redisProvider.set(key, value, timeout);
	}

	/**
	* 分布式锁
	*/
	public void lockTest(String lockey, String time) {
		if (null != time && !"".equals(time))
			timer = Integer.valueOf(time);
		Random r = new Random();
		String key = LOCK_PRIFEX + lockey + r.nextInt(100);

			redisProvider.lock(key, 1, () -> {
				log.info("加锁成功：1 秒");
				try {
					log.info("程序运行" +  100 + "毫秒");
				
				
					Thread.sleep( 2000);
				}catch(Exception e) {
					log.error("错日志",e);
				} finally {
					log.info("解锁成功：" + key);
				}		
		});		

	}
 ```