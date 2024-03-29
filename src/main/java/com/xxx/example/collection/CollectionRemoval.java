package com.xxx.example.collection;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 使用Collection类的collection.remove()方法从集合中删除某个元素
 */
public class CollectionRemoval {
    public static void main(String[] args) {
        System.out.println("Collection Example!\n");
        int size;
        HashSet<String> collection = new HashSet<String>();
        String str1 = "Yellow", str2 = "White", str3 = "Green", str4 = "Blue";
        Iterator iterator;
        collection.add(str1);
        collection.add(str2);
        collection.add(str3);
        collection.add(str4);
        System.out.print("Collection data: ");
        iterator = collection.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        collection.remove(str2);
        System.out.println("After removing [" + str2 + "]\n");
        System.out.print("Now collection data: ");
        iterator = collection.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        size = collection.size();
        System.out.println("Collection size: " + size + "\n");
    }
}
