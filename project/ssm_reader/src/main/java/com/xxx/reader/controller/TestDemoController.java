package com.xxx.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestDemoController {
    @GetMapping("/test/t1")
    public ModelAndView test1() {
        return new ModelAndView("/test");
    }

    @GetMapping("/test/t2")
    @ResponseBody
    public Map<String, String> test2() {
        Map<String, String> result  = new HashMap<>();
        result.put("test", "测试数据");
        return result;
    }
}
