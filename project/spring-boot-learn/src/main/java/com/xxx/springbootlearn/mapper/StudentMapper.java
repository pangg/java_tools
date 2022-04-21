package com.xxx.springbootlearn.mapper;

import com.xxx.springbootlearn.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
    @Select("select * from student where id = #{id}")
    Student findById(Integer in);
}
