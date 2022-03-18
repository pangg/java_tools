package com.xxx.spring.ioc7;

import com.xxx.spring.ioc7.controller.UserController;
import com.xxx.spring.ioc7.dao.EmployeeDao;
import com.xxx.spring.ioc7.dao.UserDao;
import com.xxx.spring.ioc7.service.UserService;
import org.springframework.context.annotation.*;

@Configuration  // 当前类是一个配置类，用于替代applicationContext.xml
@ComponentScan("com.xxx.spring.ioc7")
public class Config {
    @Bean  // Java Config利用方法创建对象，将方法返回对象放入容器，beanId=方法名
    @Primary
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        System.out.println("已创建：" + userDao);
        return userDao;
    }

    @Bean
    public UserDao userDao1() {
        UserDao userDao = new UserDao();
        System.out.println("已创建：" + userDao);
        return userDao;
    }

    @Bean
    // 先按name尝试注入(注入和变量名相同的方法名的对象)，
    // name不存在则按照类型注入（如果多个同类型对象，注意使用@Primary）
    public UserService userService(UserDao uDao, EmployeeDao employeeDao) {
        UserService userService = new UserService();
        System.out.println("已创建：" + userService);
        userService.setUserDao(uDao);
        System.out.println("调用setUserDao：" + uDao);
        userService.setEmployeeDao(employeeDao);
        System.out.println("调用setUserDao：" + employeeDao);
        return userService;
    }

    @Bean   // <bean id="xxx" class="xxxx" ></bean>
    @Scope("prototype")
    public UserController userController(UserService userService) {
        UserController userController = new UserController();
        System.out.println("已创建：" + userController);
        userController.setUserService(userService);
        System.out.println("调用setUserService：" + userService);
        return userController;
    }
}
