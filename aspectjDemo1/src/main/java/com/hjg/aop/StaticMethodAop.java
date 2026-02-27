package com.hjg.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Description
 * @Author hjg
 * @Date 2026-01-16 15:24
 */
@Aspect
public class StaticMethodAop {

    @Pointcut("execution(static * *(..)) && @annotation(com.hjg.aop.StatusCheck)")
    public void intfStatusPostMappingPointCut(){}

    @Around("intfStatusPostMappingPointCut()")
    public Object intfStatusPostMappingAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("=========before=========");

        Object result = pjp.proceed();
        System.out.println("original result = " + result.toString());
        String newResult = result + " Jimmy";

        System.out.println("=========after=========");
        return newResult;
    }
}
