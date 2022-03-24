### Spring MVC 环境配置 ###
    1。Maven依赖spring-webmvc；
    2。web.xml配置DispatcherServlet；
    3。配置applicationContext.xml的mvc标记；
    4。开发Controller控制器
### URL Mapping ###
    1。URL Mapping指将URL与Controller方法绑定；
    2。通过将URL与方法绑定，Spring MVC便可通过Tomcat对外暴露服务；
    3。URL Mapping注解：
        @RequestMapping 通用绑定
        @GetMapping 绑定Get请求
        @PostMapping 绑定post请求
