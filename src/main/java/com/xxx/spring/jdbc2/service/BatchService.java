package com.xxx.spring.jdbc2.service;

import com.xxx.spring.jdbc2.dao.EmployeeDao;
import com.xxx.spring.jdbc2.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BatchService {
    @Resource
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void importJob1() {
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setEno(3000 + i);
            employee.setEname("研发部员工" + i);
            employee.setSalary(4000f);
            employee.setDname("研发部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void importJob2() {
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setEno(3030 + i);
            employee.setEname("市场部员工" + i);
            employee.setSalary(4500f);
            employee.setDname("市场部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }
}
