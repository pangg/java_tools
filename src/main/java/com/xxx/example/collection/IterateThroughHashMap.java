package com.xxx.example.collection;

import java.util.*;

/**
 * 使用Collection类迭代器的方法来迭代HashMap
 */
public class IterateThroughHashMap {
    public static void main(String[] args) {
        HashMap< String, String> hMap = new HashMap< String, String>();
        hMap.put("1", "1st");
        hMap.put("2", "2nd");
        hMap.put("3", "3rd");
        hMap.put("4", "4th");
        Collection<String> cl = hMap.values();
        Iterator<String> itr = cl.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        System.out.println("------------");

        // 通过遍历map的键的方式，使用键找值进行遍历
        for (String s : hMap.keySet()) {
            System.out.println(String.format("key: %s, value: %s", s, hMap.get(s)));
        }

        System.out.println("------------");

        // 通过键值对的方式进行遍历
        Set<Map.Entry<String, String>> entries = hMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(String.format("key: %s, value: %s", entry.getKey(), entry.getValue()));
        }

        System.out.println("------------");

        // 可以使用迭代器遍历键值对的Set集合
        Iterator<Map.Entry<String, String>> iter = hMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> temp = iter.next();
            System.out.println(String.format("key: %s, value: %s", temp.getKey(), temp.getValue()));
        }

        System.out.println("------------");

        // lamda表达式遍历
        hMap.forEach((k, v) -> {
            System.out.println(String.format("key1: %s, value1: %s", k, v));
        });

        System.out.println("------------");

        // 还可对Map的值进行遍历
        Collection<String> values = hMap.values();
        for (String str : values) {
            System.out.println(str);
        }
    }
}
