package com.xxx.spring.aop2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 切面类
 */
@Component  //标记当前类为组件
@Aspect  // 说明当前类为切面类
public class MethodAspect {
    // 环绕通知，参数为PointCut切点表达式
    @Around("execution(public * com.xxx.spring.aop2..*Service.*(..))")
    public Object roundCheck(ProceedingJoinPoint pjp) throws Throwable {
        try {
            long startTime = new Date().getTime();
            Object ret = pjp.proceed(); // 执行目标方法
            long endTime = new Date().getTime();
            long duration = endTime - startTime;  // 执行时长
            if (duration >= 1000) {
                String className = pjp.getTarget().getClass().getName();
                String methodName = pjp.getSignature().getName();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                String now = sdf.format(new Date());
                System.out.println("======" + now + ":" + className + "." + methodName + "(" + duration + "ms)====");
            }
            return ret;
        } catch (Throwable e) {
            System.out.println("Exception message:" + e.getMessage());
            throw e;
        }
    }
}
