package com.xxx.oaSystem.controller;

import com.alibaba.fastjson.JSON;
import com.xxx.oaSystem.entity.Notice;
import com.xxx.oaSystem.entity.User;
import com.xxx.oaSystem.service.NoticeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "NoticeServlet", value = "/notice/list")
public class NoticeServlet extends HttpServlet {

    private NoticeService noticeService = new NoticeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("login_user");
        List<Notice> notices = noticeService.getNoticeList(user.getEmployeeId());
        Map result = new HashMap<>();
        result.put("code", "0");
        result.put("message", "");
        result.put("count", notices.size());
        result.put("data", notices);
        String json = JSON.toJSONString(result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
