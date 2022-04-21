package com.xxx.springbootlearn.controller;

import com.xxx.springbootlearn.entity.Student;
import com.xxx.springbootlearn.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StudentController {
    @Resource
    StudentService studentService;

    @GetMapping({"/student"})
    public String student(@RequestParam Integer num) {
        Student student = studentService.findStudent(num);
        System.out.println(student);
        return student.toString();
    }
}
