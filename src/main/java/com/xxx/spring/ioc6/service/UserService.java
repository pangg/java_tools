package com.xxx.spring.ioc6.service;

import com.xxx.spring.ioc6.dao.IUserDao;
import com.xxx.spring.ioc6.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 利用@Autowired自动装配的问题：
 * 在当前userDao自动装配中会扫描到两个实现IUserDao的类，都通过@Repository装配，在UserService中，不知道注入哪个类；
 * 解决方法：
 *      1。两个Dao类中删除一个类的@Repository注解
 *      2。也可以在其中一个使用@Primary注解
 *      3。最优解决方案使用 @Resource
 */
@Service
public class UserService {
     // @Autowired
    // Spring IoC容器会自动通过反射技术将属性private修饰符自动改为public，直接进行赋值
    // 不再执行set方法
    private IUserDao userDao;

    public UserService() {
        System.out.println("正在创建UserService："+ this);
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    @Autowired
    // 如果装配注解放在set方法上，则自动按类型/名称对set方法参数进行注入
    public void setUserDao(IUserDao userDao) {
        System.out.println("通过setUserDao：" + userDao);
        this.userDao = userDao;
    }
}
