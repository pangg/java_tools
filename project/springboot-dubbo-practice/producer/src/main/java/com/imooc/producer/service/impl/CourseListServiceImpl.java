package com.imooc.producer.service.impl;

import com.imooc.producer.entity.Course;
import com.imooc.producer.mapper.CourseMapper;
import com.imooc.producer.service.CourseListService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 描述：     课程列表服务实现类
 */
@Service(version = "${demo.service.version}") //指定dubbo版本
public class CourseListServiceImpl implements CourseListService {

    @Autowired
    CourseMapper courseMapper;

    public List<Course> getCourseList() {
        return courseMapper.findValidCourses();
    }

}
