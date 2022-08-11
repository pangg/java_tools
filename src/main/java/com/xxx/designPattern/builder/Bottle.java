package com.xxx.designPattern.builder;

/**
 * 包装瓶
 */
public class Bottle implements Packing{
    @Override
    public String pack() {
        return "Bottle";
    }
}
