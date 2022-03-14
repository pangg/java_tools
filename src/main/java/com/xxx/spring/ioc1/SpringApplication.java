package com.xxx.spring.ioc1;

import com.xxx.spring.ioc1.entity.Apple;
import com.xxx.spring.ioc1.entity.Child;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        // 创建spring ioc容器，并根据配置文件在容器中实例化对象
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext_1.xml");
        Apple sweetApple = context.getBean("sweetApple", Apple.class);
        System.out.println(sweetApple.getTitle());

        // 从IoC容器中提前beanId=lily的对象
        Child lily = context.getBean("lily", Child.class);
        lily.eat();
        Child andi = context.getBean("andi", Child.class);
        andi.eat();
        Child luna = context.getBean("luna", Child.class);
        luna.eat();
    }
}
