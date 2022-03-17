package com.xxx.spring.ioc6;

import com.xxx.spring.ioc6.service.DepartmentService;
import com.xxx.spring.ioc6.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IoC四种组件类型注解
 * 1。@Component 组件注解，通用注解，被该注解描述的类将被IoC容器管理并实例化
 * 2。@Controller 语义注解，说明当前类是MVC应用中的控制器类
 * 3。@Service 语义注解，说明当前类是Service业务服务类
 * 4。@Repository 语义注解，说明当前类用于业务持久层，通常描述对应的Daolei
 *
 * 两类自动装配注解
 *  按类型装配：
 *      1。@Autowired 按容器内对象类型动态注入属性，由Spring机构提供
 *      2。@Inject 基于JSR-300标准，其他同@Autowired，但不支持required属性
 *  按名称装配：
 *      1。@Named 与@Inject配合使用，JSR-300规范，按属性名自动装配属性
 *      2。@Resource 基于JSP-250规范，优先按名称，再按类型智能匹配
 *
 *
 * 元数据注解：
 *      1。@Primary 按类型装配时出现多个相同类型的对象，拥有此注解对象优先被注入；
 *      2。@PostConstruct 描述方法，相当于XML中init-method配置的注解版本；
 *      3。@PreDestroy 描述方法，相当于XML中destroy-method配置的注解版本；
 *      4。@Score 设置对象的scope属性；设置单例或多例
 *      5。@Value 为属性注入静态数据；
 *
 *
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/ioc6/applicationContext.xml");
        /*String[] ids = context.getBeanDefinitionNames();
        for (String id:ids) {
            System.out.println(id + ":" + context.getBean(id));
        }*/

        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.getUserDao());

        /*DepartmentService departmentService = context.getBean("departmentService", DepartmentService.class);
        departmentService.joinDepartment();*/

    }
}