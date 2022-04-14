package com.xxx.reader.controller;

import com.xxx.reader.entity.Evaluation;
import com.xxx.reader.entity.Member;
import com.xxx.reader.exception.BussiException;
import com.xxx.reader.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/register.html")
    public ModelAndView showRegister() {
        return new ModelAndView("/register");
    }

    @GetMapping("/login.html")
    public ModelAndView showLogin() {
        return new ModelAndView("/loginPage");
    }

    @PostMapping("/registe")
    @ResponseBody
    public Map<String, String> register(String vc, String username, String password, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        Map<String, String> map = new HashMap<>();
        if (vc == null || !vc.equalsIgnoreCase(code)) {
            map.put("code", "vc01");
            map.put("msg", "验证码错误");
        } else {
            try {
                memberService.createMember(username, password, username);
                map.put("code", "0");
                map.put("msg", "success");
            } catch (BussiException ex) {
                ex.printStackTrace();
                map.put("code", ex.getCode());
                map.put("msg", ex.getMsg());
            }
        }
        return map;
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map<String, String> checkLogin(String username, String password, String vc, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        String verifyCode = (String) session.getAttribute("kaptchaVerifyCode");
        if (vc == null || !vc.equalsIgnoreCase(verifyCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码失败");
        } else {
            try {
                Member member = memberService.checkLogin(username, password);
                session.setAttribute("loginMember", member);
                result.put("code", "0");
                result.put("msg", "success");
            } catch (BussiException e) {
                e.printStackTrace();
                result.put("code", e.getCode());
                result.put("msg", e.getMsg());
            }
        }
        return result;
    }

    @PostMapping("/update_read_state")
    @ResponseBody
    public Map<String, Object> updateMemberReadState(Long memberId, Long bookId, @RequestParam("state") Integer readState) {
        Map<String, Object> result = new HashMap<>();
        try {
            memberService.updateMemberReadState(memberId, bookId, readState);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussiException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @PostMapping("/evaluate")
    @ResponseBody
    public Map<String, Object> evaluate(Long memberId, Long bookId, Integer score, String content) {
        Map<String, Object> ret = new HashMap<>();
        try {
            Evaluation evaluation = memberService.evaluate(memberId, bookId, score, content);
            ret.put("code", "0");
            ret.put("msg", "success");
            ret.put("evaluation", evaluation);
        } catch (BussiException e) {
            e.printStackTrace();
            ret.put("code", e.getCode());
            ret.put("msg", e.getMsg());
        }
        return ret;
    }

    @PostMapping("/enjoy")
    @ResponseBody
    public Map<String, Object> enjoy(Long evaluationId) {
        Map<String, Object> ret = new HashMap<>();
        try {
            Evaluation evaluation = memberService.enjoy(evaluationId);
            ret.put("code", "0");
            ret.put("msg", "success");
            ret.put("evaluation", evaluation);
        } catch (BussiException e) {
            e.printStackTrace();
            ret.put("code", e.getCode());
            ret.put("msg", e.getMsg());
        }
        return ret;
    }
}
