package com.xxx.bean2map.pojo;

import org.apache.dubbo.common.utils.PojoUtils;

import java.util.Date;

/**
 * 当属性名比较特殊时也很容易出问题，但 dubbo 这个工具类更符合我们的预期。
 * 如当属性名叫 URL 时，转为 Map 后 key 就会被解析成  uRL。
 */
public class DubboPojoDemo {
    public static void main(String[] args) {
        MockObject mockObject = new MockObject();
        mockObject.setAInteger(1);
        mockObject.setALong(2L);
        mockObject.setADate(new Date());
        mockObject.setADouble(3.4D);
        mockObject.setParent(3L);

        Object generalize = PojoUtils.generalize(mockObject);
        System.out.println(generalize);
    }
}
