package com.xxx.spring.aop1.service;

import com.xxx.spring.aop1.dao.EmployeeDao;

public class EmployeeService {
    private EmployeeDao employeeDao;

    public void entry() {
        System.out.println("执行员工入职业务逻辑~");
        employeeDao.insert();
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
