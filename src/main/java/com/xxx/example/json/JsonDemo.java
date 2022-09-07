package com.xxx.example.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDemo {
    /**
     * 读取json字符串数据
     */
    @Test
    public void stringToJson() {
        String str = "[{\"cateId\":2,\"sort\":10,\"cateName\":\"蔬果\",\"secCategory\":[{\"cateId\":2,\"secCateId\":10001,\"sort\":20,\"secCateName\":\"西瓜\"},{\"cateId\":2,\"secCateId\":10002,\"sort\":19,\"secCateName\":\"橘子\"}]}]";

        JSONArray jsonArray = JSONArray.parseArray(str);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = JSONArray.parseObject(jsonArray.get(i).toString());
            /*System.out.println(jsonObject.getInteger("cateId"));
            System.out.println(jsonObject.getInteger("sort"));
            System.out.println(jsonObject.getString("cateName"));*/

            JSONArray jsonArraySec = JSONArray.parseArray(jsonObject.getJSONArray("secCategory").toString());
            for (int j = 0; j < jsonArraySec.size(); j++) {
                JSONObject jsonObjectSec = JSONObject.parseObject(jsonArraySec.get(j).toString());
                String spt = String.format("%d, %s, %d, %s",
                        jsonObjectSec.getInteger("cateId"),
                        jsonObject.getString("cateName"),
                        jsonObjectSec.getInteger("secCateId"),
                        jsonObjectSec.getString("secCateName"));
                System.out.println(spt);
            }
        }

        System.out.println("-----------line-----------");

        String str2 = "{\"cateId\":2,\"sort\":10,\"cateName\":\"蔬果\",\"secCategory\":[{\"cateId\":2,\"secCateId\":10001,\"sort\":20,\"secCateName\":\"西瓜\"},{\"cateId\":2,\"secCateId\":10002,\"sort\":19,\"secCateName\":\"橘子\"}]}";
        JSONObject jObj = JSONObject.parseObject(str2);
        String f1 = String.format(
                "一级类目信息：%d, %s",
                jObj.getInteger("cateId"),
                jObj.getString("cateName")
                );
        System.out.println(f1);
    }

    /**
     * String——>>>JSONArray
     * JSONArray——>>>JSONObject
     */
    @Test
    public void stringToJsonArray() {
        String st = "[{\"name\":\"Tim\",\"age\":25,\"sex\":\"male\"},{\"name\":\"Tim2\",\"age\":28,\"sex\":\"female\"}]";
        JSONArray tableData = JSONArray.parseArray(st);
        System.out.println(tableData);
        for (int i = 0; i < tableData.size(); i++) {
            JSONObject rowData = JSONObject.parseObject(tableData.get(i).toString()) ;
            System.out.println(rowData.getString("name"));
        }
    }

    /**
     * String——>>>JSONObject
     */
    @Test
    public void stringToJsonObject() {
        String st = "{\"name\":\"Tim\",\"age\":25,\"sex\":\"male\"}";
        JSONObject rowData = JSONObject.parseObject(st);
        System.out.println(rowData.getString("name"));
    }

    /**
     * JSONObject——>>>JSONArray
     */
    @Test
    public void test1() {
        //1.json字符串转换为对象
        String jsonString="{'name':'42313123','id':'2345','age':12}";
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String id = jsonObject.getString("id");
        System.out.println(id);
        //2. JSONObject转化成自定义类对象
        User peoplePo1 = JSONObject.parseObject(jsonString, User.class);
        System.out.println(peoplePo1);
        //3. JSONObject转化成Map集合
        Map map = JSONObject.parseObject(jsonString, Map.class);
        System.out.println(map);
        System.out.println("-----------------");
        //4. 自定义对象转化成json格式的字符串
        User peoplePo = new User();
        peoplePo.setId(1);
        peoplePo.setAge(11);
        peoplePo.setName("LH");
        String peopleJson = JSON.toJSONString(peoplePo);
        System.out.println(peopleJson);
        System.out.println("-------------------");
        //5. String类型转化成JSONObject;
        String str = "{\"result\":\"success\",\"message\":\"成功！\"}";
        JSONObject jsonObject1 = JSONObject.parseObject(str);
        System.out.println(jsonObject1);
        //6. JSONObject转化成JSONArray的两种方式
        String str1 = "{\"result\":\"success\",\"message\":\"成功！\",\"data\":[{\"name\":\"Tom\",\"age\":\"20\"}]}";
        JSONObject jsonToArray = JSONObject.parseObject(str1);
        System.out.println("------------------");
        //方式一
        JSONArray data = jsonToArray.getJSONArray("data");
        System.out.println(data);
        //方式二
        JSONArray jsonArray = JSONArray.parseArray(jsonToArray.getString("data"));
        System.out.println(jsonArray);
        //7. jsonArray转化成JSONObject并取出其中的元素数据
        JSONObject o = (JSONObject) jsonArray.get(0);
        String name = o.getString("name");
        System.out.println(o);
        System.out.println(name);
        System.out.println(jsonArray.toString());
    }

    /**
     * JSON.toJSONString(obejct) - java对象转JSON字符串
     * 默认情况下，如果int类型和boolean类型的属性没赋值的时候，调用 JSON.toJSONString(obejct) 序列化后返回boolean类型和int类型的默认值 false和0；
     * 当然其他类型如果没有赋值，序列化时，会被过滤掉。
     */
    @Test
    public void beanListToJson() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("测试");
        userList.add(user);
        System.out.println(JSON.toJSONString(userList));
    }

    /**
     * List集合转JSON
     */
    @Test
    public void listToJson() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("测试1", 89));
        System.out.println(JSON.toJSONString(userList));
    }

    /**
     * Map集合转JSON
     */
    @Test
    public void mapToJson() {
        Map<String, Object> users = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            users.put("user" + i, new User("name" + i, 20+i));
        }
        System.out.println(JSON.toJSONString(users));
    }

    /**
     * JSON转Java对象 - JSON.perseObject()
     */
    @Test
    public void jsonTojavaObject() {
        String json = "{\"age\":20,\"name\":\"name0\"}";
        System.out.println(JSON.parseObject(json, User.class));
    }

    /**
     * JSON转Java集合 - JSON.perseArray()
     */
    @Test
    public void jsonToList() {
        String json = "[{\"age\":20,\"name\":\"name0\"}]";
        List<User> userList = JSON.parseArray(json, User.class);
        userList.forEach(System.out::println);
    }

    /**
     * SerializerFeature.WriteMapNullValue
     * 对一个对象或者列表进行序列化时，默认情况下如果属性值为null，序列化后的结果会过滤掉其属性,如果想保留其属性值，可以使用 SerializerFeature.WriteMapNullValue。
     */
    @Test
    public void writeMapNullValueDemo() {
        User user = new User();
        user.setAge(20);
        String ret = JSON.toJSONString(user, SerializerFeature.WriteMapNullValue);
        System.out.println(ret);
    }

    /**
     * SerializerFeature.WriteNullStringAsEmpty
     * 对一个对象或者列表进行序列,把属性值为null的字段进行转化为 "" 双引号。
     */
    @Test
    public void writeNullStringAsEmptyDemo() {
        User user = new User();
        user.setAge(20);
        String res = JSON.toJSONString(user, SerializerFeature.WriteNullStringAsEmpty);
        System.out.println(res);
    }

    /**
     * SerializerFeature.WriteNullNumberAsZero
     * 序列之后, 把属性值为 null 的属性转化为 0，这个前提是此属性是 int 类型的
     */
    @Test
    public void writeNullNumberAsZeroDemo() {
        User user = new User();
        user.setName("测试");
        String res = JSON.toJSONString(user, SerializerFeature.WriteNullNumberAsZero);
        System.out.println(res);
    }

    /**
     * SerializerFeature.WriteNullBooleanAsFalse
     *  序列之后, 把属性值为 null 的属性转化为 false，这个前提是此属性是 boolean 类型的
     */
    @Test
    public void writeNullBooleanAsFalseDemo() {
        User user = new User();
        user.setName("测试");
        String res = JSON.toJSONString(user, SerializerFeature.WriteNullNumberAsZero);
        System.out.println(res);
    }

    /**
     * SerializerFeature.WriteDateUseDateFormat
     * 把时间戳序列化为正常的时间
     */
    @Test
    public void writeDateUseDateFormatDemo() {
        User user = new User();
        user.setName("测试");
        String res = JSON.toJSONString(user, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(res);
    }

    /**
     * SerializerFeature.PrettyFormat
     * 序列化的数据纵向布局
     */
    @Test
    public void prettyFormatDemo() {
        User user = new User();
        user.setName("测试");
        user.setJsonField("hah");
        String res = JSON.toJSONString(user, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(res);
    }

    /**
     * @JSonField() 注解
     * 1. 注解属性 name序列化后的名字（单独序列化，对属性名进行修改）
     * 2. 注解属性 ordinal序列化后的顺序（字段的排序）
     * 3. 注解属性 format 序列化后的格式: @JSONField(format = "YYYY-MM-dd")
     * 4. 注解属性 serialize 是否序列化该字段(默认为true，如果false，当字段值为null时，会被过滤掉)
     * 5. 使用serializeUsing来定制属性的序列化类
     */
    @Test
    public void serializeUsingDemo() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("测试,");
        userList.add(user);
        System.out.println(JSON.toJSONString(userList));
    }

    /**
     * @JSONType()注解中的属性: 只能作用在类上，也是对类里边的字段进行序列化
     * · includes 要序列化的字段（注意：如果字段上有 @serialize(true),如果没有includes字段也不会被序列化）
     * · orders序列化后的字段顺序，也是一个数组
     */

    /**
     * FastJson属性名过滤器:
     *      通过 SimplePropertyPreFilter 过滤器，来过滤指定的属性名，然后在转JSON的时候，带上过滤器参数即可
     */
    @Test
    public void simplePropertyPreFilter() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("测试");
        userList.add(user);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        // 下边方法也很好理解：调用过滤器上边的getExcludes排除字段的方法，什么字段需要排除呢：add() 添加需要排除的字段即可
        filter.getExcludes().add("health");
        filter.getExcludes().add("time");

        System.out.println(JSON.toJSONString(userList, filter));
    }
}
