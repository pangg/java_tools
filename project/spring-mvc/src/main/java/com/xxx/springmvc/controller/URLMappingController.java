package com.xxx.springmvc.controller;

import com.xxx.springmvc.entity.Form;
import com.xxx.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/um")  // 作用在类上，作为url的通用前缀，也就是当前类的请求url前都要加上"/um"
public class URLMappingController {

    @GetMapping("/g")
    // @RequestMapping(value = "/g", method = RequestMethod.GET)  //作用在方法上，不再区分get/post请求
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName, Date createTime) {
        System.out.println("managerName:" + managerName);
        System.out.println(createTime);
        return "This is get method!";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping(String username, Long password, @DateTimeFormat(pattern = "yyyy-MM-dd") Date createTime) {
        System.out.println(username + ":" + password);
        System.out.println(createTime);
        return "This is post method!这是POST响应！";
    }

    @PostMapping("/p1")
    @ResponseBody
    public String postMapping1(User user) {
        System.out.println(user.getUsername() + ":" + user.getPassword());
        System.out.println(user.getCreateTime());
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

    @GetMapping("/view")
    public ModelAndView showView(Integer userId) {
        // ModelAndView mav = new ModelAndView("/view.jsp");
        // ModelAndView mav = new ModelAndView("redirect:/view.jsp");

        /**
         *  /view.jsp 绝对路径，以应用webapp为根目录
         *  view.jsp 相对路径，会加上上面全局"/um"
         */
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/view.jsp");
        User user = new User();
        if (userId == 1) {
            user.setUsername("Lily");
        } else if (userId == 3) {
            user.setUsername("李四");
        } else if (userId == 2) {
            user.setUsername("Lina");
        }
        mav.addObject("user", user);
        return mav;
    }

    /**
     * String与ModelMap实现ModelAndView功能
     *
     * Controller方法返回String的情况
     *      1。方法被@ResponseBody描述，SpringMVC直接响应String字符串本身；
     *      2。方法不存在@ResponseBody，则SpringMVC处理String指代的视图（页面）；
     *
     * 如果页面不需要绑定数据，则就不需要ModelMap这个参数；
     *
     */
    @GetMapping("/view1")
    @ResponseBody
    public String showView1(Integer userId, ModelMap modelMap) {
        String view = "/view.jsp";
        User user = new User();
        if (userId == 1) {
            user.setUsername("Lily");
        } else if (userId == 3) {
            user.setUsername("李四-1");
        } else if (userId == 2) {
            user.setUsername("Lina");
        }
        modelMap.addAttribute("user", user);
        return view;
    }
}
