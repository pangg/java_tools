package com.xxx.example.collection;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 使用Enumeration类的hasMoreElements()和nestElement()方法来显示HashTable的内容
 */
public class DisplayHashTable {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        ht.put("1", "One");
        ht.put("2", "Two");
        ht.put("3", "Three");
        ht.put("4", "Four");
        ht.put("5", "Five");
        ht.put("6", "Six");
        Enumeration e = ht.elements();

        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
}
