package com.xxx.spring.jdbc1;

import com.xxx.spring.jdbc1.dao.EmployeeDao;
import com.xxx.spring.jdbc1.entity.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/jdbc1/applicationContext.xml");
        EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);
        Employee employee = employeeDao.findById(1);
        System.out.println(employee);
    }
}
