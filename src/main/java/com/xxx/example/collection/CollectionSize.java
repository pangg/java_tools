package com.xxx.example.collection;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 使用Collections类的collection.add()来添加新数据和使用collection.size()来获取的集合的大小
 */
public class CollectionSize {
    public static void main(String[] args) {
        System.out.println("Collection Example!\n");
        int size;
        HashSet<String> collection = new HashSet<String>();

        Iterator iterator;
        collection.add("str1");
        collection.add("str2");
        collection.add("str3");
        collection.add("str4");
        collection.add("100");
        collection.add("200");
        System.out.print("Collection data: ");
        iterator = collection.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        size = collection.size();

        if (collection.isEmpty()) {
            System.out.println("Collection is empty");
        } else {
            System.out.println("Collection size: " + size);
        }
        System.out.println();
    }
}
