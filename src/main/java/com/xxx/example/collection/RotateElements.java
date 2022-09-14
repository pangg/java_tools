package com.xxx.example.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 使用rotate()方法根据方法的第二个参数来扭转列表的元素
 */
public class RotateElements {
    public static void main(String[] args) {
        List list = Arrays.asList("1 2 3 4 5 6".split(" "));
        System.out.println("List :" + list);
        Collections.rotate(list, 2);
        System.out.println("rotate: " + list);
    }
}
