package com.xxx.reader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.reader.entity.Test;
import com.xxx.reader.mapper.TestMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MyBatisPlusTest {
    @Resource
    private TestMapper testMapper;

    @org.junit.Test
    public void testInsert() {
        Test test = new Test();
        test.setContent("MyBatis Plus 测试");
        testMapper.insert(test);
        System.out.println("插入测试数据成功～");
    }

    @org.junit.Test
    public void testUpdate() {
        Test test = testMapper.selectById(39);
        test.setContent("ttttttest");
        testMapper.updateById(test);
        System.out.println("数据更新成功");
    }

    @org.junit.Test
    public void testDelete() {
        testMapper.deleteById(39);
        System.out.println("数据删除成功～");
    }

    @org.junit.Test
    public void testSelect() {
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 40);
        queryWrapper.gt("id", 5);
        List<Test> list = testMapper.selectList(queryWrapper);
        System.out.println(list);
    }
}
