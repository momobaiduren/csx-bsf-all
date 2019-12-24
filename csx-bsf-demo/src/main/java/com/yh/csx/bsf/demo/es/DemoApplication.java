package com.yh.csx.bsf.demo.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication //implements WebMvcConfigurer
public class DemoApplication  {

//	@Autowired
//	private ElasticSearchSqlProvider searchService;
//	
//    @GetMapping("/selectByIndex")
//    public Object selectByIndex() {
//    	List<UserVo> result = searchService.searchBySql("select * from aaa where id = 5", UserVo.class);
//    	return result;
//    }
//    
//    //  SELECT * FROM indexName/type
//    @GetMapping("/selectByType")
//    public Object selectByType() {
//    	List<UserVo> result = searchService.searchBySql("select * from aaa/bbb", UserVo.class);
//    	return result;
//    }
//
//    @GetMapping("/insert")
//    public Object insert() {
//    	List<UserVo> result = Lists.newArrayList();
//    	result.add(new UserVo("-1", "liu555"));
//    	result.add(new UserVo("5", "liu666"));
//    	result.add(new UserVo(null, "liu8888"));
//    	searchService.insertData("aaa", "bbb", result);
//    	return result;
//    }
//    
//    @GetMapping("/delete")
//    public Object delete() {
//    	searchService.deleteBySql("delete from aaa where id = 5");
//    	return "";
//    }
    
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
