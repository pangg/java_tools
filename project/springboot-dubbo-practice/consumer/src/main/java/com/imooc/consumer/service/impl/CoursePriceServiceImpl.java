package com.imooc.consumer.service.impl;

import com.imooc.consumer.dao.CoursePriceMapper;
import com.imooc.consumer.entity.CourseAndPrice;
import com.imooc.consumer.entity.CoursePrice;
import com.imooc.consumer.service.CoursePriceService;
import com.imooc.producer.entity.Course;
import com.imooc.producer.service.CourseListService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：     课程 价格服务
 */
@Service // 对外暴露HTTP接口，这个service要用spring的而不是dubbo的
public class CoursePriceServiceImpl implements CoursePriceService {

    @Autowired
    CoursePriceMapper coursePriceMapper;

    @Reference(version = "${demo.service.version}")
    CourseListService courseListService;

    @Override
    public CoursePrice getCoursePrice(Integer courseId) {
        return coursePriceMapper.findCoursePrices(courseId);
    }

    @Override
    public List<CourseAndPrice> getCoursesAndPrice() {
        List<CourseAndPrice> courseAndPriceList = new ArrayList<>();
        // RPC 远程调用
        List<Course> courseList = courseListService.getCourseList();
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if (course != null) {
                CoursePrice price = getCoursePrice(course.getCourseId());
                if (price != null && price.getPrice() > 0) {
                    CourseAndPrice courseAndPrice = new CourseAndPrice();
                    courseAndPrice.setId(course.getId());
                    courseAndPrice.setCourseId(course.getCourseId());
                    courseAndPrice.setName(course.getName());
                    courseAndPrice.setPrice(price.getPrice());
                    courseAndPriceList.add(courseAndPrice);
                }
            }
        }
        return courseAndPriceList;
    }
}
