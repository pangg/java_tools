package com.xxx.spring.ioc6.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
// @Primary
public class UserOracleDao implements IUserDao{
    public UserOracleDao() {
        System.out.println("正在实例化UserOracleDao：" + this);
    }
}
