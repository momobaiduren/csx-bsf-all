package com.yh.csx.bsf.demo.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang Zhaoping
 */
@RestController
@SpringBootApplication
public class ElkApplication {

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(ElkApplication.class, args);
    }

}
