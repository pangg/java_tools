package com.xxx.spring.ioc2.factory;

import com.xxx.spring.ioc1.entity.Apple;

/**
 * 静态工厂通过静态方法创建对象，隐藏创建对象的细节
 */
public class AppleStaticFactory {
    public static Apple createSweetApple() {
        System.out.println("静态工厂创建对象");
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");
        return apple;
    }
}
