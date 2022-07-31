package com.xxx.sboot_mall.service;

import com.xxx.sboot_mall.exception.ImoocMallException;
import com.xxx.sboot_mall.model.pojo.User;

public interface UserService {
    User getUser();

    void register(String username, String password) throws ImoocMallException;

    User login(String userName, String password) throws ImoocMallException;

    void updateInformation(User user) throws ImoocMallException;

    boolean checkAdminRole(User user);
}
