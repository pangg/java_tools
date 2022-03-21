package com.xxx.spring.aop1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 切面类
 */
public class MethodAspect {
    /**
     * 切面方法用于扩展额外功能
     * JoinPoint 连接点，通过连接点可以获取目标类/方法的信息
     *
     * @param joinPoint
     */
    public void printExecutionTime(JoinPoint joinPoint) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String now = sdf.format(new Date());
        String className = joinPoint.getTarget().getClass().getName(); //获取目标类的名称
        String methodName = joinPoint.getSignature().getName(); // 获取目标方法名称
        System.out.println("----->" + now + ":" + className + ":" + methodName);

        Object[] args = joinPoint.getArgs();
        System.out.println("---->参数个数：" + args.length);
        for (Object arg : args) {
            System.out.println("---->参数：" + arg);
        }
    }

    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        System.out.println("<-----" + ret);
    }

    public void doAfterThrowing(JoinPoint joinPoint, Throwable th) {
        System.out.println("<-----" + th.getMessage());
    }

    public void doAfter(JoinPoint joinPoint) {
        System.out.println("<----" + "触发后置通知");
    }


    /**
     * 环绕通知
     * ProceedingJoinPoint是JoinPoint的升级版，在原有功能外，还可以控制目标方法是否执行
     */
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
