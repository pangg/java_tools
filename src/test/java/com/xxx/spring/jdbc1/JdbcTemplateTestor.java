package com.xxx.spring.jdbc1;

import com.xxx.spring.jdbc1.dao.EmployeeDao;
import com.xxx.spring.jdbc1.entity.Employee;
import com.xxx.spring.jdbc1.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/jdbc1/applicationContext.xml")
public class JdbcTemplateTestor {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private EmployeeService employeeService;

    @Test
    public void testFindById() {
        Employee employee = employeeDao.findById(1);
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
        employee.setEno(12);
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

    /**
     * 编程式事务
     */
    @Test
    public void testBeachInsert() {
        employeeService.batchImport();
    }

    /**
     * 声明式事物(推荐使用)
     */
    @Test
    public void testBeachInsertByTx() {
        employeeService.batchImportByTx();
    }

}
