package com.xxx.spring.aop3.dynaProxy;

import com.xxx.spring.aop3.service.UserService;
import com.xxx.spring.aop3.service.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * InvocationHandler是JDK提供的反射类，用于在JDK动态代理中对目标方法进行增强
 * InvocationHandler实现类与切面类的环绕通知类似
 */
public class ProxyInvocationHandler implements InvocationHandler {
    private Object target;
    private ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 在invoke()方法对目标方法进行增强
     * @param proxy 代理类对象
     * @param method  目标方法对象
     * @param args  目标方法实参
     * @return 目标方法运行后返回值
     * @throws Throwable 目标方法抛出的异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=====" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()) + "========");
        Object ret = method.invoke(target, args); // 调用目标方法，ProceedingJoinPoint.proceed()
        return ret;
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        ProxyInvocationHandler invocationHandler = new ProxyInvocationHandler(userService);
        // 动态代理类创建
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                invocationHandler);
        userServiceProxy.createUser();


        // 动态代理。必须实现接口才可以运行
        EmployeeService employeeService = new EmployeeServiceImpl();
        EmployeeService employeeServiceProxy = (EmployeeService)Proxy.newProxyInstance(
                employeeService.getClass().getClassLoader(),
                employeeService.getClass().getInterfaces(),
                new ProxyInvocationHandler(employeeService));
        employeeServiceProxy.createEmployee();


    }
}
