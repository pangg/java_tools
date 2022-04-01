### Spring与SpringMVC环境配置 ###
    1。依赖spring-webmvc
    2。配置DispatcherServlet
    3。启用Spring MVC注解模式；
    4。配置请求与响应字符集；
    5。配置FreeMarker模版引擎；
    6。配置Json序列化组件


### MyBatis-plus 整合 ###
    1。pom引入mybatis-plus依赖
    2。Spring XML更改配置SqlSessionFactory实现类
    3。mybatis-config.xml增加MP分页插件

### MyBatis-plus开发三部曲 ###
    1。创建实体类，@TableName/ @Tabled / @TableField 实现映射
    2。创建Mapper接口继承BaseMapper，创建Mapper XML
    3。开发时注入Mapper对象，通过内置API实现CRUD操作

### MyBatis-Plus核心注解 ###
    1。@TableName    将实体类与表明映射
    2。@TableId       说明对应属性是表的主键
    3。@TableField  设置属性和列名的对应关系