package com.xxx.oaSystem.service;

import com.xxx.oaSystem.dao.EmployeeDao;
import com.xxx.oaSystem.entity.Employee;
import com.xxx.oaSystem.utils.MybatisUtils;

/**
 * 员工服务
 */
public class EmployeeService {
    /**
     * @param employeeId
     * @return
     */
    public Employee selectById(Long employeeId) {
        return (Employee) MybatisUtils.executeQuery(sqlSession -> {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            return employeeDao.selectById(employeeId);
        });
    }
}
