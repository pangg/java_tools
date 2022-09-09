package com.xxx.example.dataStructure;

import java.util.Collections;
import java.util.Vector;

/**
 * 使用Vector类的v.add()方法和Collection类的sort.Collection()方法对向量执行二分法搜索查询
 */
public class VectorBinary {
    public static void main(String[] args) {
        Vector<String> v = new Vector<>();
        v.add("Y");
        v.add("I");
        v.add("I");
        v.add("B");
        v.add("A");
        v.add("I");
        Collections.sort(v);
        System.out.println(v);
        int index = Collections.binarySearch(v, "I");
        System.out.println("Element found at : " + index);
    }
}
