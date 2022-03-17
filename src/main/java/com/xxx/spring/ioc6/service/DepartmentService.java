package com.xxx.spring.ioc6.service;

import com.xxx.spring.ioc6.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepartmentService {
    /**
     * 1. @Resource设置name属性，则按照name在IoC容器中将bean注入
     * 2。@Resource未设置name属性：
     *      a。以属性名作为bean name在IoC容器中匹配bean，如有匹配则注入；
     *      b。按属性名未匹配，则按类型进行匹配，同@Autowired，需要加入@Primary解决类型冲突；
     *
     * 使用建议：在使用 @Resource对象时推荐设置name或者保证属性名与bean名称一致；
     */
//    @Resource(name = "userOracleDao")
//    private IUserDao udao;

    @Resource
    private IUserDao userOracleDao;

    public void joinDepartment()
    {
        System.out.println("joinDepartment:" + userOracleDao);
    }
}
