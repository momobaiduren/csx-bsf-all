package com.yh.csx.bsf.cat;

import java.io.File;
import com.yh.csx.bsf.core.util.PropertyUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * @author: chejiangyi
 * @version: 2019-05-28 14:31
 **/
public class CatApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {            
            String catdir = System.getProperty(CatProperties.UserDir)+File.separator+"catlogs"+File.separator;
            PropertyUtils.setDefaultInitProperty(CatApplicationContextInitializer.class,CatProperties.Project, CatProperties.CatHome,catdir);
    }

}
