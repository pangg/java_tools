package com.xxx.spring.ioc6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IoC四种组件类型注解
 * 1。@Component 组件注解，通用注解，被该注解描述的类将被IoC容器管理并实例化
 * 2。@Controller 语义注解，说明当前类是MVC应用中的控制器类
 * 3。@Service 语义注解，说明当前类是Service业务服务类
 * 4。@Repository 语义注解，说明当前类用于业务持久层，通常描述对应的Daolei
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/ioc6/applicationContext.xml");
        String[] ids = context.getBeanDefinitionNames();
        for (String id:ids) {
            System.out.println(id + ":" + context.getBean(id));
        }

    }
}
