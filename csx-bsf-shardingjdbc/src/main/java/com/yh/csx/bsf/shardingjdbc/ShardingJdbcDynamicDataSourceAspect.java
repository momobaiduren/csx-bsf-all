package com.yh.csx.bsf.shardingjdbc;

import com.yh.csx.bsf.core.base.BsfException;
import com.yh.csx.bsf.shardingjdbc.base.DataSource;
import com.yh.csx.bsf.shardingjdbc.base.SlaveOnly;

import java.lang.annotation.Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author: chejiangyi
 * @version: 2019-09-01 14:25
 **/
@Aspect
public class ShardingJdbcDynamicDataSourceAspect {
    @Pointcut("@within(com.yh.csx.bsf.shardingjdbc.base.MasterOnly) " +
            "|| @annotation(com.yh.csx.bsf.shardingjdbc.base.MasterOnly)")
    public void masterOnly() {

    }

    @Around("masterOnly()")
    public Object handleMasterOnly(ProceedingJoinPoint joinPoint) throws Throwable {
       return ShardingJdbcUtils.hitMasterOnly(()-> {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    throw new BsfException(e);
                }
            }
        );
    }

    @Pointcut("@within(com.yh.csx.bsf.shardingjdbc.base.SlaveOnly) " +
            "|| @annotation(com.yh.csx.bsf.shardingjdbc.base.SlaveOnly)")
    public void slaveOnly() {

    }

    @Around("slaveOnly()")
    public Object handleSlaveOnly(ProceedingJoinPoint joinPoint) throws Throwable {
    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    	Annotation  annotation = methodSignature.getMethod().getAnnotation(SlaveOnly.class);  
    	if(annotation==null) {
    		annotation=joinPoint.getSignature().getDeclaringType().getAnnotation(SlaveOnly.class);
    	}   	
        return ShardingJdbcUtils.hitSlaveOnly(((SlaveOnly)annotation).slave(),()-> {
                    try {
                        return joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new BsfException(e);
                    }
                }
        );
    }

    @Pointcut("@within(com.yh.csx.bsf.shardingjdbc.base.DataSource) " +
            "|| @annotation(com.yh.csx.bsf.shardingjdbc.base.DataSource)")
    public void dataSource() {

    }

    @SuppressWarnings("unchecked")
	@Around("dataSource()")
    public Object handleDataSource(ProceedingJoinPoint joinPoint) throws Throwable {    	
    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    	Annotation  annotation = methodSignature.getMethod().getAnnotation(DataSource.class);  
    	if(annotation==null) {
    		annotation=joinPoint.getSignature().getDeclaringType().getAnnotation(DataSource.class);
    	}
        return ShardingJdbcUtils.hitDataSource(((DataSource)annotation).name(),()-> {
                    try {
                        return joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new BsfException(e);
                    }
                }
        );
    }
}
