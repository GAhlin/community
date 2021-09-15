package com.example.community.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ahlin
 * @date 2021/9/15 15:25
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class WebLogAop {

    @Pointcut("@annotation(com.example.community.annotation.WebLog)")
    public void webLog() {
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        WebLog webLog = signature.getMethod().getAnnotation(WebLog.class);
        log.info("{},参数{}", webLog.description(), args);
        Object result = joinPoint.proceed();
        log.info("{}完成，结果：{},耗时{}毫秒", webLog.description(), result, System.currentTimeMillis() - start);
        return result;
    }
}
