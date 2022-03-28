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

### 请求参数接收 ###
    1。使用Controller方法接收参数；
    2。使用Java Bean接收数据

### WEB应用中的中文乱码 ###
    1。Tomcat8以前默认字符集为ISO-8859-1(8.0之后版本默认utf-8字符集)，属于西欧字符集，需要转为UTF-8
    2。中文乱码配置
        a。Get请求乱码 - server.xml(tomcat的config目录下)增加URIEncoding属性；
            <Connector port="8080" protocol="HTTP/1.1"
            connectionTimeout="20000"
            redirectPort="8443" URIEncoding="UTF-8" />

        b。Post请求乱码 - web.xml配置CharacterEncodingFilter
            <filter>
                <filter-name>characterFilter</filter-name>
                <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
                <init-param>
                    <param-name>encoding</param-name>
                    <param-value>UTF-8</param-value>
                </init-param>
            </filter>
            <filter-mapping>
                <filter-name>characterFilter</filter-name>
                <url-pattern>/*</url-pattern>
            </filter-mapping>

        c。Response响应乱码 - Spring配置StringHttpMessageConverter
            <mvc:annotation-driven conversion-service="conversionService">
                <mvc:message-converters>
                    <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                        <property name="supportedMediaTypes">
                            <list>
                                <!--在Servlet中配置：response.setContentType("text/html;charset=utf-8")-->
                                <value>text/html;charset=utf-8</value>
                            </list>
                        </property>
                    </bean>
                </mvc:message-converters>
            </mvc:annotation-driven>

### 响应中产生结果 ###
    1。@ResponseBody - 产生响应文本：
        a。直接产生响应体的数据，过程不涉及任何视图；
        b。可产生标准字符串/JSON/XML等格式数据；
        c。@ResponseBody被StringHttpMessageConverter所影响；
    2。ModelAndView - 利用模板引擎渲染输出：
        a。指"模型(数据)与视图(界面)"对象；
        b。通过ModelAndView可将包含数据对象与模板引擎进行绑定；
        c。SpringMVC中默认的View是JSP，也可以配置其他模版引擎；

### ModelAndView ###
    1。mav.addObject()方法设置的属性默认存放在当前请求中；
    2。默认ModelAndView使用请求转发（forward）至页面；
    3。重定向使用new ModelAndView("redirect:/index.jsp");
