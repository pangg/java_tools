package com.xxx.oaSystem.service;

import com.xxx.oaSystem.dao.RbacDao;
import com.xxx.oaSystem.dao.UserDao;
import com.xxx.oaSystem.entity.Node;
import com.xxx.oaSystem.entity.User;
import com.xxx.oaSystem.service.exception.BusinessException;
import com.xxx.oaSystem.utils.MD5Utils;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    private RbacDao rbacDao = new RbacDao();

    /**
     * 根据前台输入的用户名进行登陆验证
     * @param username
     * @param password
     * @return 校验通过，返回包含用户数据的User实体
     */
    public User checkLogin(String username, String password) {
        // 按用户名查询用户
        User user = userDao.selectByUsername(username);
        if (user == null) {
            throw new BusinessException("L001", "用户名不存在");
        }

        // 对前台输入的密码加盐混淆后生成MD5，与保存在数据库中的MD5密码进行比对
        String md5 = MD5Utils.md5Digest(password, user.getSalt());
       if (!md5.equals(user.getPassword())) {
            throw new BusinessException("L002", "密码错误");
        }
        return user;
    }

    public List<Node> selectNodeByUserId(Long userId) {
        return rbacDao.selectNodeByUserId(userId);
    }
}
