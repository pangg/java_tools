package com.xxx.designPattern.bridge;

/**
 * 创建实现DrawAPI接口的具体桥接实现者类
 */
public class GreenCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", " + y + "]");
    }
}
