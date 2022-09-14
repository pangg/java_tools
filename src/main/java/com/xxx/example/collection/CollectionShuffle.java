package com.xxx.example.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 使用Collections类的Collections.shuffle()方法来清理打乱集合中的元素
 */
public class CollectionShuffle {
    public static void main(String[] args) {
        ArrayList<String> obj = new ArrayList<String>();
        obj.add("A");
        obj.add("B");
        obj.add("C");
        obj.add("D");
        obj.add("E");
        obj.add("F");
        Collections.shuffle(obj);
        System.out.println(obj);
    }
}
