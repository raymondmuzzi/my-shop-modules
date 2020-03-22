package com.raymondmuzzi.my.shop.web.admin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Sql execution time statistic aspect for dao layer
 *
 * @author raymondmuzzi
 * @date 2020-03-21 01:01:10
 */
@Aspect
@Component
public class SqlExecutionTimeAspects {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlExecutionTimeAspects.class);

    @Pointcut("execution(* com.raymondmuzzi.my.shop.web.admin.dao.*.*(..))")
    public void pointcut() {
    }

    /**
     * Calculate the sql time span of each sql
     *
     * @param joinPoint the join point
     * @return the target method's invocation result
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object sqlExecutionStatistic(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        LOGGER.info("SqlExecutionStatistic: {}'s execution time is {}ms", joinPoint.getSignature(), (end - start));
        return result;
    }
}