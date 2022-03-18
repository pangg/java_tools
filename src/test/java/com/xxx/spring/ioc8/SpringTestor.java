package com.xxx.spring.ioc8;

import com.xxx.spring.ioc8.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Spring Test 测试模块
 *      1。Spring Test是Spring中用于测试的模块；
 *      2。Spring Test对JUnit单元测试框架有着良好的整合
 *      3。通过Spring Test可在JUnit在单元测试时自动初始化IoC容器
 */

// 将Junit4的执行权交由Spring Test，在测试用例执行前自动初始化IoC容器
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ioc8/applicationContext.xml"})
public class SpringTestor {
    @Resource
    private UserService userService;

    @Test
    public void testUserService() {
        userService.createUser();
    }
}
