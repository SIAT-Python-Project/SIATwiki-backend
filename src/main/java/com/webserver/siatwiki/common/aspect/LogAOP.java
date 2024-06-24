package com.webserver.siatwiki.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogAOP {

    @Pointcut("execution(* com.webserver.siatwiki..api.*.*(..))")
    private void cut(){

    }

    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        log.info("==================== Method Start ====================");
        Object[] args = joinPoint.getArgs();

        log.info("method name: {}", getMethodName(joinPoint));

        if (args.length == 0) {
            log.info("no parameter");
        }

        for (Object arg : args) {
            log.info("parameter type: {}", arg.getClass().getSimpleName());
            log.info("parameter value: {}", arg);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {
        log.info("return type = {}", returnObj.getClass().getSimpleName());
        log.info("return value = {}", returnObj);

        log.info("==================== Method Finish ====================");
    }


    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getName();
    }
}
