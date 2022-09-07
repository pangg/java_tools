package com.xxx.example.json;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

@JSONType(includes = {"name","time", "health", "age"}, orders = {"age","name","time","health"})
public class User {
    private int id;
    private String name;
    private int age;

    @JSONField(name = "jd", ordinal = 3)
    private String jsonField = "t";

    public boolean health;

    @JSONField(format = "YYYY-MM-dd")
    public Date time = new Date();

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJsonField() {
        return jsonField;
    }

    public void setJsonField(String jsonField) {
        this.jsonField = jsonField;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", health=" + health +
                ", time=" + time +
                '}';
    }
}

class SerializeUsingFn implements ObjectSerializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object fieldValue, Object fieldName, Type fieldType, int i) throws IOException {
        System.out.println(fieldValue); // 测试,
        System.out.println(fieldName); // name
        System.out.println(fieldType); // String
        System.out.println(i); // 0
        String name = (String) fieldValue; // 向下转型，获取到属性值
        String filterName = name + "呵呵"; // 这里可以对name属性进行定制化
        jsonSerializer.write(filterName); // 调用write方法
    }
}
