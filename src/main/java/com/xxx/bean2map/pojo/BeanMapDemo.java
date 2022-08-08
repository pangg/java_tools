package com.xxx.bean2map.pojo;

import net.sf.cglib.beans.BeanMap;

import java.util.Date;

/**
 * 类型对了，但是属性名依然不对
 */
public class BeanMapDemo {
    public static void main(String[] args) {
        MockObject mockObject = new MockObject();
        mockObject.setAInteger(1);
        mockObject.setALong(2L);
        mockObject.setADate(new Date());
        mockObject.setADouble(3.4D);
        mockObject.setParent(3L);

        BeanMap beanMap = BeanMap.create(mockObject);
        System.out.println(beanMap);
    }
}
