package com.xxx.designPattern.facade;

/**
 * 门面模式(或外观模式)
 * 使用装饰类来绘制各种类型的形状。
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
