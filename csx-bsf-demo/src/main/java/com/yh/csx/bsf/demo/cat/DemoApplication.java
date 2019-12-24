package com.yh.csx.bsf.demo.cat;

import com.dianping.cat.util.Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.yh.csx.bsf.demo.cat.dao.CustomerMapper;
import com.yh.csx.bsf.demo.cat.model.Customer;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = "com.yh.csx.bsf.demo.cat")
@MapperScan(basePackages="com.yh.csx.bsf.demo.cat.dao")
//implements WebMvcConfigurer
public class DemoApplication  {

	@Autowired(required = false)
	private CustomerMapper mapper;
	
    @GetMapping("/hello")
    public Object hello(Long id) {
        Transaction t = Cat.newTransaction("MY-TRANSACTION","test in TransactionTest");
        try{
            Cat.logEvent("EVENT-TYPE-1","EVENT-NAME-1");

            // ....

        }catch(Exception e){
            Cat.logError(e);
            t.setStatus(e);
        }finally {
            t.setStatus(Transaction.SUCCESS);
            t.complete();
        }
        Customer customer2 = mapper.selectByPrimaryKey(1L);
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCustomerName("hahahahahahahahahahahahahahahahahahahahahahahahahahahahahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        return mapper.updateByPrimaryKeySelective(customer);
    }

	public static void main(String[] args) {
       // system.getenv().put("CAT_HOME","dddd");
       // String a= system.getenv("CAT_HOME");
		SpringApplication.run(DemoApplication.class, args);
        String aa = (String) Properties.forString().fromSystem().fromEnv().getProperty("CAT_HOME","");
        aa="";
	}

}
