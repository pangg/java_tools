package com.xxx.spring.ioc4;

import com.xxx.spring.ioc4.dao.UserDao;
import com.xxx.spring.ioc4.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean scope属性
 */
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/ioc4/applicationContext.xml");

        /*// 单例
        UserDao userDao1 = context.getBean("userDao1", UserDao.class);
        System.out.println(userDao1);
        UserDao userDao2 = context.getBean("userDao1", UserDao.class);
        System.out.println(userDao2);
        // 多例
        UserDao userDao3 = context.getBean("userDao2", UserDao.class);
        UserDao userDao4 = context.getBean("userDao2", UserDao.class);*/

        // 单例
        // UserService userService = context.getBean("userService1", UserService.class);

        // UserService userService = context.getBean("userService2", UserService.class);
        UserService userService = context.getBean("userService3", UserService.class);
    }
}
