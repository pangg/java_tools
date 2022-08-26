package com.xxx.designPattern.observer;

/**
 * 观察者模式
 *      在对象之间存在一对多关系时使用，例如，如果一个对象被修改，它的依赖对象将被自动通知。
 *
 * @See https://www.yiibai.com/design_pattern/observer_pattern.html
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
