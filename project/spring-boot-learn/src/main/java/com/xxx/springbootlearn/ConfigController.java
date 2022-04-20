package com.xxx.springbootlearn;

import com.xxx.springbootlearn.config.SchoolConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConfigController {
    @Resource
    SchoolConfig schoolConfig;

    @GetMapping({"/grade"})
    public String gradeClass() {
        return "年级：" + schoolConfig.grade + "， 班级：" + schoolConfig.classNum;
    }
}
