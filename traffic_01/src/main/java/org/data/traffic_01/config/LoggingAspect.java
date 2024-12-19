package org.data.traffic_01.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* org.data.traffic_01.*(..))")
    public void before(JoinPoint joinPoint) {
        // 실행되는 메서드 호출
        log.info("==Log Before : " + joinPoint.getSignature().getName()+"==");
    }

    @After("execution(* org.data.traffic_01.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info("==Log After : " + joinPoint.getSignature().getName()+"==");
    }

}
