# shardingjdbc

## 介绍
本系统由于同一分装Sharding JDBC并扩展功能，为业务提供方便。

关于Sharding jdbc更多详情，请参阅 [shardingjdbc](https://shardingsphere.apache.org/document/current/cn/manual/sharding-jdbc/configuration/config-spring-boot/) 配置文档

## 依赖引用
```java 
<dependency>
	<artifactId>csx-bsf-shardingjdbc</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>
```


## 配置说明
```shell
#启用shardingjdbc
bsf.shardingjdbc.enabled=true
#支持注解读写分析配置
#@MasterOnly 与@SlaveOnly指定主库或从库
bsf.shardingjdbc.aspect.enabled=true
# 多数据源使用时,使用@DataSource注解指定数据源
# 多数据源时，需要配置自定义hint策略算法
spring.shardingsphere.sharding.default-database-strategy.hint.algorithm-class-name=com.yh.csx.bsf.shardingjdbc.DataSourceShardingAlgorithm
```

## 示例代码

```
/**
*  指定主库
*/
@MasterOnly
@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 获取客户详细信息
	 * 
	 * @param id
	 * @return
	 */
	@MasterOnly
	public Customer getCustomer(Long id) {
		Customer customer = null;
		customer = customerMapper.selectByPrimaryKey(id);
		customer.setUpdateTime(new Date());

		return customer;
	}
}
```
```
/**
*  指定从库
*/
@SlaveOnly(1)
@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 获取客户详细信息
	 * 
	 * @param id
	 * @return
	 */
	@SlaveOnly(1)
	public Customer getCustomer(Long id) {
		Customer customer = null;
		customer = customerMapper.selectByPrimaryKey(id);
		customer.setUpdateTime(new Date());

		return customer;
	}
}
```
```
/**
* 指定数据源main0
*/
@DataSource(name="main0")
@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 获取客户详细信息
	 * 
	 * @param id
	 * @return
	 */
	@DataSource(name="main0")
	public Customer getCustomer(Long id) {
		Customer customer = null;
		customer = customerMapper.selectByPrimaryKey(id);
		customer.setUpdateTime(new Date());

		return customer;
	}
}
```
