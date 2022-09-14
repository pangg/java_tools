package com.xxx.example.collection;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 使用Enumeration类的keys()方法来获取HashTable的键
 */
public class KeysFromHashTable {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        ht.put("1", "One");
        ht.put("2", "Two");
        ht.put("3", "Three");
        ht.put("4", "Four");
        ht.put("5", "Five");
        Enumeration e = ht.keys();

        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
}
