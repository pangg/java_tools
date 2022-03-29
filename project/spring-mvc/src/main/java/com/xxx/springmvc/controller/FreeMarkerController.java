package com.xxx.springmvc.controller;

import com.xxx.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm")
public class FreeMarkerController {
    @GetMapping("/test")
    public ModelAndView showTest() {
        System.out.println("～～～～～");
        ModelAndView mav = new ModelAndView("/test");
        User user = new User();
        user.setUsername("andy");
        mav.addObject("u", user);
        return mav;
    }
}
