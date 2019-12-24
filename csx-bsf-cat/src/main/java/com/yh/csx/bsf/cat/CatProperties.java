package com.yh.csx.bsf.cat;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author: chejiangyi
 * @version: 2019-08-12 11:12
 **/
@ConfigurationProperties
public class CatProperties {
    @Value("${cat.server.url:}")
    @Getter
    private String catServerUrl;
    public static String Prefix="cat.";
    public static String Project="Cat";
    public static String SpringApplicationName = "spring.application.name";
    public static String UserDir="user.dir";
    public static String CatHome="CAT_HOME";
    public static String CatServerUrl="cat.server.url";
}
