package com.xxx.springmvc.controller;

import com.xxx.springmvc.entity.Form;
import com.xxx.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/um")  // 作用在类上，作为url的通用前缀，也就是当前类的请求url前都要加上"/um"
public class URLMappingController {

    @GetMapping("/g")
    // @RequestMapping(value = "/g", method = RequestMethod.GET)  //作用在方法上，不再区分get/post请求
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName) {
        System.out.println("managerName:" + managerName);
        return "This is get method!";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping(String username, Long password) {
        System.out.println(username + ":" + password);
        return "This is post method!";
    }

    @PostMapping("/p1")
    @ResponseBody
    public String postMapping1(User user) {
        System.out.println(user.getUsername() + ":" + user.getPassword());
        return "This is post method~~!";
    }

    /**
     * 通过bean和方法参数同时接收请求数据
     */
    @PostMapping("/p2")
    @ResponseBody
    public String postMapping2(User user, String username) {
        System.out.println(user.getUsername() + ":" + user.getPassword() + ":" + username);
        return "This is post method!---";
    }

    /**
     * 接收表单复合数据：通过数组接收
     */
    @PostMapping("/apply")
    @ResponseBody
    public String apply(String name, String course, Integer[] purpose) {
        System.out.println(name);
        System.out.println(course);
        System.out.println(purpose);
        for (Integer p : purpose) {
            System.out.println(p);
        }

        return "success";
    }

    /**
     * 指定参数和默认值
     */
    @PostMapping("/apply2")
    @ResponseBody
    public String apply2(@RequestParam(value = "n", defaultValue = "ANOT") String name, String course, Integer[] purpose) {
        System.out.println(name);
        System.out.println(course);
        for (Integer p : purpose) {
            System.out.println(p);
        }
        return "success";
    }

    /**
     * 接收表单复合数据：通过List集合接收（推荐使用）
     */
    @PostMapping("/apply3")
    @ResponseBody
    public String apply3(String name, String course, @RequestParam List<Integer> purpose) {
        System.out.println(name);
        System.out.println(course);
        for (Integer p : purpose) {
            System.out.println(p);
        }
        return "success";
    }

    /**
     * 接收表单复合数据：通过实体接收
     */
    @PostMapping("/apply4")
    @ResponseBody
    public String apply4(Form form) {
        System.out.println(form.getName());
        System.out.println(form.getCourse());
        for (Integer p : form.getPurpose()) {
            System.out.println(p);
        }
        return "success";
    }

    /**
     * Map接收参数，不支持复合数据（数组只能接收第一个参数）
     */
    @PostMapping("/apply5")
    @ResponseBody
    public String apply5(@RequestParam Map maps) {
        System.out.println(maps);

        return "success";
    }

    /**
     * 关联对象赋值
     */
    @PostMapping("/delivery")
    @ResponseBody
    public String delivery(Form form) {
        System.out.println(form.getName());
        System.out.println(form.getCourse());
        for (Integer p : form.getPurpose()) {
            System.out.println(p);
        }
        System.out.println(form.getDelivery());
        return "success";
    }

}
