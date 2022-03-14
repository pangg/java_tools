package com.xxx.spring.ioc1.entity;

public class Apple {
    private String title;
    private String color;
    private String origin;
    private Float price;

    public Apple() {
        System.out.println("Apple对象已创建：" + this);
    }

    public Apple(String title, String color, String origin) {
        System.out.println("通过带参方法实例化对象1：" + this);
        this.title = title;
        this.color = color;
        this.origin = origin;
    }

    public Apple(String title, String color, String origin, Float price) {
        System.out.println("通过带参方法实例化对象2：" + this);
        this.title = title;
        this.color = color;
        this.origin = origin;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
