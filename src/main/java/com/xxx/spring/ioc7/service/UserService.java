package com.xxx.spring.ioc7.service;


import com.xxx.spring.ioc7.dao.EmployeeDao;
import com.xxx.spring.ioc7.dao.UserDao;

public class UserService {
    private UserDao userDao;
    private EmployeeDao employeeDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
