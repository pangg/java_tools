package com.xxx.sboot_mall.controller;

import com.xxx.sboot_mall.common.ApiRestResponse;
import com.xxx.sboot_mall.common.Constant;
import com.xxx.sboot_mall.exception.ImoocMallExceptionEnum;
import com.xxx.sboot_mall.model.pojo.User;
import com.xxx.sboot_mall.model.request.AddCategoryRequest;
import com.xxx.sboot_mall.service.CategoryService;
import com.xxx.sboot_mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @ApiOperation("后台添加目录")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryRequest addCategoryRequest) {
        System.out.println(addCategoryRequest);
        User currentUser = (User) session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currentUser == null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        // 校验管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole) {
            categoryService.add(addCategoryRequest);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }

    }
}
