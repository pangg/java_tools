package com.xxx.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/um")  // 作用在类上，作为url的通用前缀，也就是当前类的请求url前都要加上"/um"
public class URLMappingController {

    //@GetMapping("/g")
    @RequestMapping(value = "/g", method = RequestMethod.GET)  //作用在方法上，不再区分get/post请求
    @ResponseBody
    public String getMapping() {
        return "This is get method!";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping() {
        return "This is post method!";
    }
}
