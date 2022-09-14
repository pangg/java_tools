package com.xxx.example.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 使用replaceAll()方法来替换列表中所有出现的元素
 */
public class ReplaceElement {
    public static void main(String[] args) {
        List list = Arrays.asList("11 22 33 44 55 66 11 33 44 33".split(" "));
        System.out.println("List :" + list);
        Collections.replaceAll(list, "33", "888");
        System.out.println("replaceAll: " + list);
    }
}
