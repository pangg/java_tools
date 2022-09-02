package com.xxx.example.method;

/**
 * 使用覆盖子类继承的方法
 */
public class MethodOverriding {
    public static void main(String[] args) {
        Figure f = new Figure(11, 12);
        Rectangle r = new Rectangle(19, 15);
        Figure figref;
        figref = f;
        System.out.println("Area is :" + figref.area());
        figref = r;
        System.out.println("Area is :" + figref.area());
    }
}

class Figure {
    double dim1;
    double dim2;

    Figure(double a, double b) {
        dim1 = a;
        dim2 = b;
    }

    Double area() {
        System.out.println("Inside area for figure.");
        return (dim1 * dim2);
    }
}

class Rectangle extends Figure {
    Rectangle(double a, double b) {
        super(a, b);
    }

    Double area() {
        System.out.println("Inside area for rectangle.");
        return (dim1 * dim2);
    }
}