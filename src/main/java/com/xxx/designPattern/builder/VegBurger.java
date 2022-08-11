package com.xxx.designPattern.builder;

/**
 * 素菜汉堡
 */
public class VegBurger extends Burger{
    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
