package com.xxx.spring.ioc4;

import com.xxx.spring.ioc4.entity.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean scope属性
 * Bean 生命周期
 */
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/ioc4/applicationContext.xml");
        System.out.println("========IoC容器已经初始化=========");

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
        // UserService userService = context.getBean("userService3", UserService.class);

        /**
         * Bean 生命周期
         * 1。IoC容器准备初始化解析Xml
         * 2。对象实例化，执行构造方法；
         * 3。为对象注入属性；
         * 4。调用init-method初始化方法
         * 5。IoC容器初始化完毕；
         * 6。执行业务代码；
         * 7。IoC容器准备销毁；
         * 8。调用destory-method方法释放资源；
         * 9。IoC容器销毁完毕；
         */
        Order order = context.getBean("order1", Order.class);
        order.pay();

        ((ClassPathXmlApplicationContext) context).registerShutdownHook();
    }
}
