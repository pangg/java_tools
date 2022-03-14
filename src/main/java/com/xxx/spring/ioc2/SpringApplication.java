package com.xxx.spring.ioc2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 三种XML实例化Bean的配置方式
 * 1。 基于构造方法实例化对象
 * 2。 基于静态工厂实例化对象
 * 3。 基于工厂实例方法实例化对象
 */
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext_2.xml");

    }
}
