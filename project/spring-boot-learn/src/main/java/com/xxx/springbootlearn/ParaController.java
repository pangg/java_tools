package com.xxx.springbootlearn;

import org.springframework.web.bind.annotation.*;

/**
 * 演示各种传参
 */
@RestController
@RequestMapping("/prefix")  // 路由前缀
public class ParaController {
    @GetMapping({"/first"})
    public String firstRequest() {
        return "First Spring Boot Api~";
    }

    @GetMapping({"/requestpara"})
    public String requestPara(@RequestParam Integer num) {
        return "para from request: " + num;
    }

    @GetMapping({"/para/{num}"})
    public String pathPara(@PathVariable Integer num) {
        return "para from path:" + num;
    }

    /**
     * 多URL请求
     */
    @GetMapping({"/multiurl1", "/multiurl2"})
    public String multiurl(@RequestParam Integer num) {
        return "param from request:" + num;
    }

    /**
     * 参数默认值
     */
    @GetMapping({"/required"})
    public String required(@RequestParam(required = false, defaultValue = "0") Integer num) {
        return "param from request:" + num;
    }
}
