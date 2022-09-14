package com.xxx.example.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 使用Collections类的min()方法和max()方法来查找列表的最小值和最大值
 */
public class FindMinAndMaxInList {
    public static void main(String[] args) {
        List list = Arrays.asList("11 111 22 333 444 55 666 77 8".split(" "));
        System.out.println(list);
        System.out.println("max: " + Collections.max(list));
        System.out.println("min: " + Collections.min(list));
    }
}
