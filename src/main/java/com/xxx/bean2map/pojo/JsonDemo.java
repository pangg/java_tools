package com.xxx.bean2map.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Date;
import java.util.Map;

public class JsonDemo {
    /**
     * 将 Java Bean 转 Map 最常见的手段就是使用  JSON 框架，如 fastjson 、 gson、jackson 等。
     * 但使用 JSON 将 Java Bean 转 Map 会导致部分数据类型丢失。
     * 存在问题：
     * （1） 通过 fastjson 将 Java Bean 转为 Map ，类型会发生转变。如 Long 变成 Integer ，Date 变成 Long, Double 变成  Decimal 类型等。
     *
     * （2）在某些场景下，Map 的  key 并非和属性名完全对应，像是通过 get set 方法“推断”出来的属性名。
     *
     * @param args
     */
    public static void main(String[] args) {
        MockObject mockObject = new MockObject();
        mockObject.setAInteger(1);
        mockObject.setALong(2L);
        mockObject.setADate(new Date());
        mockObject.setADouble(3.4D);
        mockObject.setParent(3L);

        String json = JSON.toJSONString(mockObject);
        Map<String, Object> map = JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
        System.out.println(map);
    }
}
