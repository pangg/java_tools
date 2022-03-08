package com.xxx.oaSystem.controller;


import com.xxx.oaSystem.entity.Department;
import com.xxx.oaSystem.entity.Employee;
import com.xxx.oaSystem.entity.Node;
import com.xxx.oaSystem.entity.User;
import com.xxx.oaSystem.service.DepartmentService;
import com.xxx.oaSystem.service.EmployeeService;
import com.xxx.oaSystem.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/index")
public class IndexServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final EmployeeService employeeService = new EmployeeService();
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login_user");
        Employee employee = employeeService.selectById(user.getEmployeeId());
        Department department = departmentService.selectById(employee.getDepartmentId());
        // 获取当前登陆用户可用功能模块列表
        List<Node> nodeList = userService.selectNodeByUserId(user.getUserId());
        request.setAttribute("node_list", nodeList);

        session.setAttribute("current_employee", employee);
        session.setAttribute("current_department", department);
        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
