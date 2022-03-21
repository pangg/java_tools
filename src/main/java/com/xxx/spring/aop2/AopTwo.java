package com.xxx.spring.aop2;

import com.xxx.spring.aop2.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTwo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/aop2/applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.createUser();
    }
}
