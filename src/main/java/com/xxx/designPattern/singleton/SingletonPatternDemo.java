package com.xxx.designPattern.singleton;

/**
 * 单例模式
 * @See https://www.yiibai.com/design_pattern/singleton_pattern.html
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {
        //illegal construct
        //Compile Time Error: The constructor SingleObject() is not visible
        //SingleObject object = new SingleObject();

        //Get the only object available
        SingleObject object = SingleObject.getInstance();

        //show the message
        object.showMessage();
    }
}
