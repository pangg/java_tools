package com.xxx.spring.aop1;

import com.xxx.spring.aop1.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Spring AOP与AspectJ的关系
 *      1。Eclipse AspectJ，一种基于java平台的面向切面编程的语言
 *      2。Spring AOP使用AspectJWeaver实现类与方法匹配
 *      3。Spring AOP利用代理模式实现对象运行时功能扩展；
 *
 *  概念
 *      1。Aspect 切面，具体的可插拔组件功能，通常一个切面只实现一个通用功能；
 *      2。Target Class/Method 目标类、目标方法指真正要执行与业务相关的方法；
 *      3。PointCut 切入点，使用execution表达式说明切面要作用在系统的哪些类上；
 *      4。JoinPoint 连接点，切面运行过程中是包含了目标类/方法元数据的对象；
 *      5。Advice 通知，说明具体的切面的执行时机，Spring包含了5种不同类型通知；
 *
 *  AOP配置过程
 *      1。依赖AspectJ；
 *      2。实现切面类/方法；
 *      3。配置Aspect Bean；
 *      4。定义PointCut；
 *      5。配置Advice；
 *
 *  JoinPoint核心方法
 *      1。Object getTarget() 获取IoC容器内目标对象
 *      2。Signature getSignature() 获取目标方法
 *      3。Object[] getArgs()  获取目标方法参数
 *
 *
 *  PointCut切点表达式
 *      public void com.xxx.spring.aop1.service.UserService.createUser(形参1,形参2)
 *      execution(public * com.xxx..*.*(..))
 *      * 通配符
 *      .. 包通配符
 *      (..) 参数通配符
 *
 *  五种通知类型
 *      1。Before Advice  前置通知，目标方法运行前执行
 *      2。After Returning Advice  返回后通知目标方法，返回数据后执行
 *      3。After Throwing Advice   异常通知，目标方法抛出异常后执行
 *      4。After Advice   后置通知，目标方法运行后执行
 *      5。Around Advice  最强大通知，自定义通知执行时机，可决定目标方法是否运行
 *
 * 特殊通知 - 引介增强
 */
public class AopOne {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/aop1/applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.createUser();
        userService.generateRandPassword("MD5", 16);
    }
}
