package com.xxx.spring.ioc4.entity;

public class Order {
    private Float price;
    private Integer quantity;
    private Float total;

    public Order() {
        System.out.println("创建Order对象：" + this);
    }

    public void init() {
        System.out.println("执行init()方法");
        total = price * quantity;
    }

    public void pay() {
        System.out.println("订单金额：" + total);
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        System.out.println("设置金额price：" + price);
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        System.out.println("设置数量quantity：" + quantity);
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public void destory() {
        System.out.println("销毁容器并释放资源～");
    }
}
