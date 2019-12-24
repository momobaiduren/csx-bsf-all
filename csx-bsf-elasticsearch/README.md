# ElasticSearch集成

## 介绍
ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java语言开发的，并作为Apache许可条款下的开放源码发布，是一种流行的企业级搜索引擎。
[更多详情](https://www.elastic.co/cn/products/elasticsearch)

本系统用于统一封装ES的客户端，简化业务使用。
## 依赖引入

```java 
<dependency>
	<artifactId>csx-bsf-elasticsearch</artifactId>
	<groupId>com.yh.csx.bsf</groupId>
	<version>1.7.1-SNAPSHOT</version>
</dependency>
```
## 配置说明


## 代码示例

ElasticSearch集成demo
```
	@Autowired
	private ElasticSearchSqlService searchService;
	
    @GetMapping("/selectByIndex")
    public Object selectByIndex() {
    	List<UserVo> result = searchService.searchBySql("select * from aaa where id = 5", UserVo.class);
    	return result;
    }
    
    //  SELECT * FROM indexName/type
    @GetMapping("/selectByType")
    public Object selectByType() {
    	List<UserVo> result = searchService.searchBySql("select * from aaa/bbb", UserVo.class);
    	return result;
    }

    @GetMapping("/insert")
    public Object insert() {
    	List<UserVo> result = Lists.newArrayList();
    	result.add(new UserVo("-1", "liu555"));
    	result.add(new UserVo("5", "liu666"));
    	result.add(new UserVo(null, "liu8888"));
    	searchService.insertData("aaa", "bbb", result);
    	return result;
    }
    
    @GetMapping("/delete")
    public Object delete() {
    	searchService.deleteBySql("delete from aaa where id = 5");
    	return "";
    }
    
 ```