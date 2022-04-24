package com.xxx.sboot_mall.service.impl;

import com.xxx.sboot_mall.model.dao.UserMapper;
import com.xxx.sboot_mall.model.pojo.User;
import com.xxx.sboot_mall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }
}
