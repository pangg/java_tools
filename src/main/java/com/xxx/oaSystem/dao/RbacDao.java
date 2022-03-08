package com.xxx.oaSystem.dao;

import com.xxx.oaSystem.entity.Node;
import com.xxx.oaSystem.utils.MybatisUtils;

import java.util.List;

public class RbacDao {
    public List<Node> selectNodeByUserId(Long userId) {
        return  (List) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectList("rbacmapper.selectNodeByUserId", userId));
    }
}
