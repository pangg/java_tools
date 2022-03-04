package com.xxx.oaSystem.service;

import com.xxx.oaSystem.dao.UserDao;
import com.xxx.oaSystem.entity.User;
import com.xxx.oaSystem.service.exception.BusinessException;

public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 根据前台输入的用户名进行登陆验证
     * @param username
     * @param password
     * @return 校验通过，返回包含用户数据的User实体
     */
    public User checkLogin(String username, String password) {
        User user = userDao.selectByUsername(username);
        if (user == null) {
            throw new BusinessException("L001", "用户名不存在");
        }
        if (!password.equals(user.getPassword())) {
            throw new BusinessException("L002", "密码错误");
        }
        return user;
    }
}
