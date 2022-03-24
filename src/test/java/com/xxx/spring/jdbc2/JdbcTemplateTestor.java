package com.xxx.spring.jdbc2;

import com.xxx.spring.jdbc2.dao.EmployeeDao;
import com.xxx.spring.jdbc2.entity.Employee;
import com.xxx.spring.jdbc2.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/jdbc2/applicationContext.xml")
public class JdbcTemplateTestor {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private EmployeeService employeeService;

    @Test
    public void testFindById() {
        Employee employee = employeeDao.findById(2);
        System.out.println(employee);
    }

    @Test
    public void testFindByDname() {
        System.out.println(employeeDao.findByDname("测试"));
    }

    @Test
    public void testFindMapByDname() {
        System.out.println(employeeDao.findMapByDname("测试"));
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setEno(13);
        employee.setEname("王武");
        employee.setSalary(888.99f);
        employee.setDname("研发");
        employee.setHiredate(new Date());
        employeeDao.insert(employee);
    }

    @Test
    public void testUpdate() {
        Employee employee = new Employee();
        employee.setEno(12);
        employee.setEname("王武2");
        employee.setSalary(9999.99f);
        employee.setDname("研发");
        employee.setHiredate(new Date());
        int count = employeeDao.update(employee);
        System.out.println(count);
    }

    @Test
    public void testDelete() {
        System.out.println(employeeDao.delete(1));
    }

    @Test
    public void testBeachInsert() {
        employeeService.batchImport();
    }


    @Test
    public void testStartImportJob() {
        employeeService.startImportJob();
    }

}
