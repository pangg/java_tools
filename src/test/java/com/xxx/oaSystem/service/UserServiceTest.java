package com.xxx.oaSystem.service;

import com.xxx.oaSystem.entity.User;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService = new UserService();

    @Test
    public void testCheckLogin1() {
        userService.checkLogin("uu", "1234");
    }

    @Test
    public void testCheckLogin2() {
        userService.checkLogin("m8", "1234");
    }

    @Test
    public void testCheckLogin3() {
        User user = userService.checkLogin("m8", "test");
        System.out.println(user);
    }
}