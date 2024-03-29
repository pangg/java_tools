package com.xxx.designPattern.builder;

/**
 * 食品接口
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
