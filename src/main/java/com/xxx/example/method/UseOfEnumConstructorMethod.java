package com.xxx.example.method;

/**
 * 使用构造函数和getPrice()方法初始化枚举并显示枚举值
 */
public class UseOfEnumConstructorMethod {
    public static void main(String[] args) {
        System.out.println("All car prices: ");
        for (Car2 c : Car2.values()) {
            System.out.println(c + " costs " + c.getPrice()
                    + " thousand dollars.");
        }
    }
}

enum Car2 {
    lamborghini(900), tata(2), audit(50), fiat(15), honda(12);
    private int price;

    Car2(int p) {
        price = p;
    }

    int getPrice() {
        return price;
    }
}
