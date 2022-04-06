package com.xxx.reader.controller;

import com.xxx.reader.exception.BussiException;
import com.xxx.reader.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/register.html")
    public ModelAndView showRegister(){
        return new ModelAndView("/register");
    }

    public Map<String, String> register(String vc, String username, String password, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        Map<String, String> map=new HashMap<>();
        if(vc == null||code == null|| !vc.equalsIgnoreCase(code)){
            map.put("code","vc01");
            map.put("msg","验证码错误");
        }else{
            try{
                memberService.createMember(username,password,username);
                map.put("code","0");
                map.put("msg","success");
            }catch (BussiException ex){
                ex.printStackTrace();
                map.put("code",ex.getCode());
                map.put("msg",ex.getMsg());
            }
        }
        return map;
    }

}
