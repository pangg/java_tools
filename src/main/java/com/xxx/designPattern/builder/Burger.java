package com.xxx.designPattern.builder;

/**
 * 汉堡抽象类
 */
public abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
