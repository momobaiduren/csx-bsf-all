package com.yh.csx.bsf.cat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

import feign.Request;
/**
 * Feign RPC 调用
 * Robin.Wang
 * @date 2019-11-19
 * 
 * */
@Aspect
public class CatFeignRpcAspect {

	@Around("execution(public * org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient.execute(..))")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Request request = (Request) proceedingJoinPoint.getArgs()[0];

		String url = request.url();
		int indexOf = url.indexOf('?');
		if (indexOf > 0) {
			url = url.substring(0, indexOf);
		}
		Transaction t = Cat.newTransaction("RPC", url);

		Object returnObj = null;
		try {
			returnObj = proceedingJoinPoint.proceed();
			t.setStatus(Transaction.SUCCESS);
		} catch (Throwable throwable) {
			Cat.logError(throwable);
			throw throwable;
		} finally {
			t.complete();
		}
		return returnObj;
	}
}
