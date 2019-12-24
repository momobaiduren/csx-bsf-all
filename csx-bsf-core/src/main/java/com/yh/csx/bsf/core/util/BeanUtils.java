package com.yh.csx.bsf.core.util;

import com.yh.csx.bsf.core.base.BsfException;
import lombok.val;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;


/**
 * @author: chejiangyi
 * @version: 2019-08-12 21:39
 **/
public class BeanUtils {
    public static void registerBean(String name, Class clazz,
                                     Object... args) {
        val applicationContext = ContextUtils.getApplicationContext();
        checkRegisterBean(applicationContext,name,clazz);
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (Object arg : args) {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
    }

    public static void registerBean(String name, Class clazz,
                                     BeanDefinitionBuilder beanDefinitionBuilder) {
        val applicationContext = ContextUtils.getApplicationContext();
        checkRegisterBean(applicationContext,name,clazz);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);

    }

    public static void unRegisterBean(String name) {
        val applicationContext = ContextUtils.getApplicationContext();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.removeBeanDefinition(name);

    }

    private static void checkRegisterBean(ApplicationContext applicationContext, String name, Class clazz){

        if(applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return;
            } else {
                throw new BsfException("BeanName 重复注册" + name);
            }
        }
    }
}
