package com.xxx.spring.ioc6.dao;

import org.springframework.stereotype.Repository;

/**
 * 组件类型注解默认beanId为类名首字母小写
 * beanId=userDao
 * 或者在Repository("udao")设置beanId
 */
@Repository
public class UserDao implements IUserDao {
    public UserDao() {
        System.out.println("正在创建UserDao："+this);
    }
}
