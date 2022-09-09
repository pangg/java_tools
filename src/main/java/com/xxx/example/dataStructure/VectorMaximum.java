package com.xxx.example.dataStructure;

import java.util.Collections;
import java.util.Vector;

/**
 * 使用Collection类的Collections.max()方法和Vector类的v.add()方法获取向量中的最大元素
 */
public class VectorMaximum {
    public static void main(String[] args) {
        Vector<Double> v = new Vector<Double>();
        v.add(new Double("13.1234"));
        v.add(new Double("13.332"));
        v.add(new Double("13.1342"));
        v.add(new Double("13.349"));
        v.add(new Double("12.321"));
        Object obj = Collections.max(v);
        System.out.println("The max element is:" + obj);
    }
}
