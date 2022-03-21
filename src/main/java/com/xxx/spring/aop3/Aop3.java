package com.xxx.spring.aop3;

import com.xxx.spring.aop3.service.UserService;
import com.xxx.spring.aop3.service.UserServiceImpl;
import com.xxx.spring.aop3.service.UserServiceProxy;
import com.xxx.spring.aop3.service.UserServiceProxy1;

/**
 * Spring AOP实现原理
 *      1。目标类拥有接口，通过JDK动态代理实现功能扩展；
 *      2。目标类没有接口，通过CGLib组件实现功能扩展；
 *
 * 代理模式
 *      代理模式通过代理对象对原对象的实现功能扩展；
 *
 *
 *
 */
public class Aop3 {
    public static void main(String[] args) {
        UserService userService = new UserServiceProxy1(new UserServiceProxy(new UserServiceImpl()));
        userService.createUser();
    }
}
