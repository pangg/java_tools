package com.xxx.oaSystem.service;

import com.xxx.oaSystem.dao.DepartmentDao;
import com.xxx.oaSystem.entity.Department;
import com.xxx.oaSystem.utils.MybatisUtils;

public class DepartmentService {
    /**
     * @param departmentId
     * @return
     */
    public Department selectById(Long departmentId) {
        return (Department)MybatisUtils.executeQuery(sqlSession -> {
            DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
            return  departmentDao.selectById(departmentId);
        });
    }
}
