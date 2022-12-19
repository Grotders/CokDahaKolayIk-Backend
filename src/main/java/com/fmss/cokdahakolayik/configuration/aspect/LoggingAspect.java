package com.fmss.cokdahakolayik.configuration.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Around(value = "@annotation(ToLog)")
    public Object log(ProceedingJoinPoint jp) throws Throwable{
        Object args = jp.getArgs();
        String methodName = jp.getSignature().getName();
        log.info("{} metodu -> parametreleri: {}", methodName, args);
        return jp.proceed();
    }

    // inactive çok fazla log basıyor -> 10 izin için 10 satır
    @AfterReturning(value = "@annotation(ToLog)", returning = "returnedValue")
    public void log(Object returnedValue) {
        if (returnedValue instanceof Collection<?>) {
            for (var value : (Collection) returnedValue) {
                log.info("#####Returned: {}", value);
            }
        } else {
            log.info("#####Returned: {}", returnedValue);
        }
    }
}
