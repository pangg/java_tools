package com.xxx.spring.aop3.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServiceProxy1 implements UserService{
    // 持有委托对象
    private UserService userService;

    public UserServiceProxy1(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUser() {
        userService.createUser();
        System.out.println("==========后置扩展功能==========");
    }
}
