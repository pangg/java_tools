package com.xxx.spring.jdbc1.dao;

import com.xxx.spring.jdbc1.entity.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public Employee findById(Integer eno) {
        // 查询单条数据
        String sql = "select * from t_employee where eno = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{eno}, new BeanPropertyRowMapper<>(Employee.class));
        return employee;
    }

    public List<Employee> findByDname(String danme) {
        String sql = "select * from t_employee where dname = ?";
        // 查询复合数据
        List<Employee> list = jdbcTemplate.query(sql, new Object[]{danme}, new BeanPropertyRowMapper<>(Employee.class));
        return list;
    }

    //将查询结果作为Map进行封装
    public List<Map<String, Object>> findMapByDname(String dname) {
        String sql = "select eno as empno, salary as s from t_employee where dname = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{dname});
        return maps;
    }

    // 利用update方法实现数据库写入操作
    public void insert(Employee employee) {
        String sql = "insert into t_employee (eno,ename,salary,dname,hiredate) values (?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                employee.getEno(), employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate()
        });
    }

    public int update(Employee employee) {
        String sql = "update t_employee set ename=?, salary=?,dname=?,hiredate=? where eno=?";
        int count = jdbcTemplate.update(sql, new Object[]{
                employee.getEname(), employee.getSalary(), employee.getDname(), employee.getHiredate(), employee.getEno()

        });
        return count;
    }

    public int delete(Integer eno) {
        String sql = "delete from t_employee where eno=?";
        return jdbcTemplate.update(sql, new Object[]{eno});
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
