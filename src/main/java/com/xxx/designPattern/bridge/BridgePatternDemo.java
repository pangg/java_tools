package com.xxx.designPattern.bridge;

/**
 * 桥接模式: 当需要将抽象与其实现去耦合时使用桥接解耦（分离），使得两者可以独立地变化.
 * 使用Shape和DrawAPI类来绘制不同的彩色圆形。
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
