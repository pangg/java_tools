package com.xxx.springbootlearn.service;

import com.xxx.springbootlearn.entity.Student;
import com.xxx.springbootlearn.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService {
    @Resource
    StudentMapper studentMapper;

    public Student findStudent(Integer id) {
        return studentMapper.findById(id);
    }
}
