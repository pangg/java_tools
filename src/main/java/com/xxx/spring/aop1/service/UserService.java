package com.xxx.spring.aop1.service;

import com.xxx.spring.aop1.dao.UserDao;

public class UserService {
    private UserDao userDao;

    public void createUser() {
        /*if (true) {
            throw new RuntimeException("该用户已存在～");
        }*/
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行创建用户业务逻辑～");
        userDao.insert();
    }

    public String generateRandPassword(String type, Integer length) {
        System.out.println("按" + type + "方式生成" + length + "位随机密码～");
        return "afsdWERSDsd";
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
