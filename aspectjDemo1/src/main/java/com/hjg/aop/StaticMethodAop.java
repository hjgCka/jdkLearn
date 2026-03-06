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

    @Pointcut("@annotation(statusCheck)")
    public void intfStatusPostMappingPointCut(StatusCheck statusCheck){}

    @Around("intfStatusPostMappingPointCut(statusCheck)")
    public Object intfStatusPostMappingAroundAdvice(ProceedingJoinPoint pjp, StatusCheck statusCheck) throws Throwable {
        System.out.println("=========before=========");

        Object result = pjp.proceed();
        System.out.println("original result = " + result.toString());
        String newResult = result + " Jimmy";

        Object[] args = pjp.getArgs();
        int index = statusCheck.urlIndex();
        Object param = args[index];
        System.out.println("param = " + param);

        System.out.println("=========after=========");
        return newResult;
    }
}
