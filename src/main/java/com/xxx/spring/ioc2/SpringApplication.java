package com.xxx.spring.ioc2;

import com.xxx.spring.ioc1.entity.Apple;
import com.xxx.spring.ioc1.entity.Child;
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
        /**
         * classpath:applicationContext.xml路径表达式
         * 1。classpath:config.xml  扫描classpath跟路径(不包含jar)的config.xml
         * 2。classpath:com/xxx/config.xml    扫描classpath下(不包含jar)com.xxx包中的config.xml
         * 3。classpath*:com/xxx/config.xml   扫描classpath下(包含jar)com.xxx包中的config.xml
         * 4。classpath:config-*.xml          扫描classpath跟路径下所有以config-开头的xml文件
         * 5。classpath:com\/**\/config.xml(不包含转义符号)   扫描com包下(包含任何子包)的config.xml
         * 6。file:c:/config.xml                           扫描c盘跟路径config.xml
         */

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

        /**
         * 基于setter方法注入对象
         */
        Child lily = context.getBean("lilyObj", Child.class);
        lily.eat();

        /**
         * 基于构造方法注入对象
         */
        Child andy = context.getBean("andyObj", Child.class);
        andy.eat();
    }
}
