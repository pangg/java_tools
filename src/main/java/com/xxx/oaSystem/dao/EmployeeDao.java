package com.xxx.oaSystem.dao;

import com.xxx.oaSystem.entity.Employee;

public interface EmployeeDao {
    public Employee selectById(Long employeeId);
}
