package com.xxx.spring.ioc5;

import com.xxx.spring.ioc5.context.ApplicationContext;
import com.xxx.spring.ioc5.context.ClassPathXmlApplicationContext;
import com.xxx.spring.ioc5.entity.Apple;

/**
 * 实现极简IoC容器
 */
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Apple apple = (Apple) context.getBean("sweetApple");
        System.out.println(apple);
    }
}
