package com.xxx.oaSystem.controller;

import com.alibaba.fastjson.JSON;
import com.xxx.oaSystem.entity.User;
import com.xxx.oaSystem.service.UserService;
import com.xxx.oaSystem.service.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/check_login")
public class LoginServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userService.checkLogin(username, password);
            result.put("code", "0");
            result.put("message", "success");
        } catch (BusinessException ex) {
            logger.error(ex.getMessage(), ex);
            result.put("code", ex.getCode());
            result.put("message", ex.getMessage());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.put("code", ex.getClass().getSimpleName());
            result.put("message", ex.getMessage());
        }
        // 返回结果
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }
}
