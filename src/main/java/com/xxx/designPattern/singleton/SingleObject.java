package com.xxx.designPattern.singleton;

/**
 * 单例类的构造函数是私有的，并且具有自身的静态实例。
 */
public class SingleObject {
    // create an object of SingleObject
    private static SingleObject instance = new SingleObject();

    //make the constructor private so that this class cannot be instantiated
    private SingleObject() {}

    //Get the only object available
    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!!!");
    }
}
