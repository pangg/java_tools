package com.xxx.spring.ioc2.factory;

import com.xxx.spring.ioc1.entity.Apple;

/**
 * 工厂实例方法创建对象是指IoC容器对工厂类进行实例化并调用对象的实例方法创建对象的过程
 */
public class AppleFactoryInstance {
    public Apple createSweetApple() {
        System.out.println("通过工厂实例方法创建对象");
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");
        return apple;
    }
}
