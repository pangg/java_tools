package com.xxx.spring.jdbc1.service;

import com.xxx.spring.jdbc1.dao.EmployeeDao;
import com.xxx.spring.jdbc1.entity.Employee;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;

public class EmployeeService {
    private EmployeeDao employeeDao;
    private DataSourceTransactionManager transactionManager;
    private BatchService batchService;

    /**
     * 声明式事物
     */
    public void batchImportByTx() {
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                throw new RuntimeException("意料之外的异常～");
            }
            Employee employee = new Employee();
            employee.setEno(9020 + i);
            employee.setEname("员工" + i);
            employee.setSalary(4000f);
            employee.setDname("市场部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }

    /**
     * 编程式事务
     */
    public void batchImport() {
        // 定义了事物默认的标准配置
        TransactionDefinition definition = new DefaultTransactionDefinition();
        // 开始了一个事物，返回事物状态，事物状态说明当前事物的执行阶段
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            for (int i = 1; i <= 10; i++) {
                /*if (i == 3) {
                    throw new RuntimeException("意料之外的异常～");
                }*/
                Employee employee = new Employee();
                employee.setEno(8000 + i);
                employee.setEname("员工" + i);
                employee.setSalary(4000f);
                employee.setDname("市场部");
                employee.setHiredate(new Date());
                employeeDao.insert(employee);
            }

            // 提交事物
            transactionManager.commit(status);
        } catch (RuntimeException e) {
            // 回滚事物
            transactionManager.rollback(status);
            throw e;
        }
    }

    public void startImportJob() {
        batchService.importJob1();
        if (true) {
            throw new RuntimeException("意料之外的异常～");
        }
        batchService.importJob2();
        System.out.println("批量导入成功");
    }

    public BatchService getBatchService() {
        return batchService;
    }

    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public DataSourceTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
