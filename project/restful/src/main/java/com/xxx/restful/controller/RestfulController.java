package com.xxx.restful.controller;

import com.xxx.restful.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 简单请求和非简单请求
 *      简单请求是指标准结构的http请求，对应的get/post请求
 *      非简单请求是复杂要求的http请求，指put/delete、扩展标准请求
 *      两者最大的区别是非简单请求发送前需要发送预检请求
 *
 * 浏览器的同源策略：
 *      同源策略阻止从一个域加载的脚本去获取另一个域上的资源；
 *      只要协议、域名、端口有任何一个不同，都被当作是不同的域；
 *      浏览器Console看到Access-Control-Allow-Origin就代表跨域了；
 *
 * HTML中允许跨域的标签：
 *      <img>  显示远程图片
 *      <script>   加载远程js
 *      <link>     加载远程css
 *
 * Spring MVC 解决跨域访问
 *      1. @CrossOrigin - Controller跨域注解；
 *      2. <mvc:cors>  -   Spring MVC全局跨域配置；
 *      3. 如果以上两种（全局和局部注解）都配置了，则优先以局部注解配置的为准；
 *
 */

// @Controller
@RestController   // @RestController注解相当于@ResponseBody和@Controller的结合
@RequestMapping("/restful")
@CrossOrigin(origins = {"http://localhost:8080", "http://www.baidu.com"})
// @CrossOrigin(origins = "*", maxAge = 3600)  // 允许所有网站跨域请求，不推荐使用；maxAge是指非简单请求的预检请求缓存时间
public class RestfulController {
    @GetMapping("/request")
    //@ResponseBody
    public String doGetRequest() {
        return "{\"message\":\"返回查询结果～\"}";
    }

    @PostMapping("/request")
    //@ResponseBody
    public String doPostRequest() {
        return "{\"message\":\"数据新建成功～\"}";
    }

    @PutMapping("/request")
    //@ResponseBody
    public String doPutRequest(Person person) {
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据更新成功～\"}";
    }

    @DeleteMapping("/request")
    //@ResponseBody
    public String doDeleteRequest() {
        return "{\"message\":\"数据删除成功～\"}";
    }

    // POST  /article/1
    // POST  /restful/request/100
    @PostMapping("/request/{rid}")
    public String doPostRequest(@PathVariable("rid") Integer requestId, Person person) {
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据新建成功～\",\"id\":"+ requestId +"}";
    }

    @GetMapping("/person")
    public Person findByPersonId(Integer id) {
        Person p = new Person();
        if (id == 1) {
            p.setName("lily");
            p.setAge(23);
        } else if (id == 2) {
            p.setName("smith");
            p.setAge(22);
        }
        return p;
    }

    @GetMapping("/persons")
    public List<Person> findPersons() {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("lily");
        p1.setAge(23);
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("smith");
        p2.setAge(22);
        p2.setBirthday(new Date());

        list.add(p1);
        list.add(p2);
        return list;
    }

    @GetMapping("/maps")
    public Map<String, String> findMaps() {
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "bbbb");
        map.put("ccc", "dddd");
        return map;
    }
}
