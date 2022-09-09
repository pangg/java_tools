package com.xxx.example.dataStructure;

import java.util.Collections;
import java.util.Vector;

/**
 * 交换向量中的两个元素
 */
public class VectorSwap {
    public static void main(String[] args) {
        Vector<String> v = new Vector<>();
        v.add("1");
        v.add("2");
        v.add("3");
        v.add("4");
        v.add("5");
        System.out.println(v);
        Collections.swap(v, 0, 4);
        System.out.println("After swapping");
        System.out.println(v);
    }
}
