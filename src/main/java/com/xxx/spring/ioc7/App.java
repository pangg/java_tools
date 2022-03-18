package com.xxx.spring.ioc7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Java Config核心注解
 *      1。@Configuration 描述类，说明当前类是Java Config配置类，完全替代XML文件；
 *      2。@Bean 描述方法，方法返回的对象将被IoC容器管理，beanId默认为方法名；
 *      3。@ImportResource 描述类，加载静态文件，可使用@Value注解获取；
 *      4。@ComponentScan 描述类，同步XML的<context:compoment-scan>标签
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("=============================");

        String[] ids = context.getBeanDefinitionNames();
        for (String id:ids) {
            System.out.println(id + ":" +context.getBean(id));
        }
    }
}
