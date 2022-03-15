package com.xxx.spring.ioc2;

import com.xxx.spring.ioc1.entity.Apple;
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
        // 多applicationContext配置文件加载
        //String[] contextXmls = new String[] {"classpath:applicationContext_1.xml", "classpath:applicationContext_2.xml"};
        //ApplicationContext context = new ClassPathXmlApplicationContext(contextXmls);

        // 初始化IoC容器并实例化对象
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext_2.xml");

        Apple apple4 = context.getBean("apple4", Apple.class);
        System.out.println(apple4.getTitle());
        Apple apple3 = (Apple) context.getBean("apple3");
        System.out.println(apple3.getTitle());

        Apple apple = context.getBean("com.xxx.spring.ioc1.entity.Apple", Apple.class);
        System.out.println(apple.getOrigin());
    }
}
