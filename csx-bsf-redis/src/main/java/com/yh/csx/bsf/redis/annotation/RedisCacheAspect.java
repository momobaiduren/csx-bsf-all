package com.yh.csx.bsf.redis.annotation;

import com.yh.csx.bsf.redis.RedisException;
import com.yh.csx.bsf.redis.RedisProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Huang Zhaoping
 */
@Aspect
public class RedisCacheAspect {


    private static final String REDIS_CACHE_KEY = "method-cache:";
    private static SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    private RedisProvider redisProvider;

    public RedisCacheAspect(RedisProvider redisProvider) {
        this.redisProvider = redisProvider;
    }

    @Around("@annotation(redisCache)")
    public Object proceedRedisCache(ProceedingJoinPoint point, RedisCache redisCache) throws Throwable {
        String key = redisCache.key();
        if (key == null || (key = key.trim()).length() == 0) {
            key = getDefaultKey(point);
        } else if (key.indexOf("#") >= 0) {
            key = getSpelKey(point, key);
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Type returnType = signature.getMethod().getGenericReturnType();
        return redisProvider.cache(key, redisCache.timeout(), () -> {
            try {
                return point.proceed();
            } catch (Throwable e) {
                throw new RedisException(e);
            }
        }, returnType);
    }

    private String getDefaultKey(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> clazz = signature.getDeclaringType();
        StringBuilder sb = new StringBuilder();
        sb.append(REDIS_CACHE_KEY).append(clazz.getSimpleName()).append(".").append(signature.getMethod().getName());
        Object[] args = point.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                sb.append("_");
                if (arg != null && arg.getClass().isArray()) {
                    sb.append(Arrays.toString((Object[]) arg));
                } else {
                    sb.append(arg);
                }
            }
        }
        return sb.toString();
    }

    private String getSpelKey(ProceedingJoinPoint point, String spel) {
        Expression expression = spelExpressionParser.parseExpression(spel);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariables(getParameters(point));
        return String.valueOf(expression.getValue(context));
    }

    private Map<String, Object> getParameters(ProceedingJoinPoint point) {
        Object[] paramValues = point.getArgs();
        if (paramValues == null || paramValues.length == 0) {
            return Collections.emptyMap();
        }
        Signature signature = point.getStaticPart().getSignature();
        String[] paramNames;
        if (signature instanceof CodeSignature) {
            paramNames = ((CodeSignature) signature).getParameterNames();
        } else {
            paramNames = new String[paramValues.length];
            for (int i = 0; i < paramValues.length; i++) {
                paramNames[i] = "arg" + i;
            }
        }
        Map<String, Object> params = new LinkedHashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            params.put(paramNames[i], paramValues[i]);
        }
        return params;
    }

}
