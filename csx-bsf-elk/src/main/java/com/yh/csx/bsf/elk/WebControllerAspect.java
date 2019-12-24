package com.yh.csx.bsf.elk;

import com.yh.csx.bsf.core.util.ContextUtils;
import com.yh.csx.bsf.core.util.JsonUtils;
import com.yh.csx.bsf.core.util.PropertyUtils;
import com.yh.csx.bsf.core.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author: chejiangyi
 * @version: 2019-08-21 14:49
 * 切面获取入参和出参
 */
@Aspect
@Slf4j
public class WebControllerAspect {

    private static final String[] tokenKeys={"token","login-token","logintoken"};

    @Pointcut("@within(org.springframework.stereotype.Controller) " +
            "|| @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        Exception exception = null; Object result=null;long timeSpan=0;
        HttpServletRequest request =  RequestContextHolder.getRequestAttributes()==null?null:((ServletRequestAttributes)RequestContextHolder.getRequestAttributes() ).getRequest();
        try {
            long start = System.currentTimeMillis();
            result = joinPoint.proceed();
            timeSpan = System.currentTimeMillis()-start;
        }catch (Exception e){
            exception=e;
            throw e;
        }
        finally {
            if(PropertyUtils.getPropertyCache(ElkProperties.BsfWebControllerAspectEnabled,false)) {
                if(request!=null) {
                    String uri = request.getRequestURI().replace(request.getContextPath(), "");
                    String inPutParam = preHandle(joinPoint, request);
                    String outPutParam = postHandle(result);
                    String ip = getRemoteHost(request);
                    log.info("【远程ip】{},【url】{},【输入】{},【输出】{},【异常】{},【耗时】{}ms", ip, uri, inPutParam, outPutParam,
                            exception == null ? "无" : com.yh.csx.bsf.core.util.StringUtils.nullToEmpty(exception.getMessage()),timeSpan);
                }
            }
        }

        return result;
    }

    private String preHandle(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Annotation[] annotations = targetMethod.getAnnotations();
        StringBuilder sb = new StringBuilder();
        //try get token
        for(val tokenKey:tokenKeys) {
            String token = request.getHeader(tokenKey);
            if (StringUtils.isNotBlank(token)) {
                sb.append("token:" + token + ",");
                break;
            }
        }
        for (Annotation annotation : annotations) {
            //log.info("1-{}", annotation.annotationType().toString());
            if (annotation.annotationType().toString().indexOf("org.springframework.web.bind.annotation") == -1) {
                continue;
            }
            sb.append(JsonUtils.serialize(request.getParameterMap()));
        }
        return sb.toString();
    }

    /**
     * 返回数据
     *
     * @param retVal
     * @return
     */
    private String postHandle(Object retVal) {
        if (null == retVal) {
            return "";
        }
        return JsonUtils.serialize(retVal);
    }

    private String getRemoteHost(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

}