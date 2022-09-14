package com.xxx.example.collection;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * 使用Collection类的Collection.min()和Collection.max()方法将字符串转换为树形集合来比较集合中的元素
 */
public class CollectionComparison {
    public static void main(String[] args) {
        String[] coins = { "Penny", "nickel", "dime", "Quarter", "dollar" };
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < coins.length; i++) {
            set.add(coins[i]);
        }

        System.out.println(Collections.min(set));
        System.out.println(Collections.min(set, String.CASE_INSENSITIVE_ORDER));


        for (int i = 0; i <= 10; i++)
            System.out.print('-');
        System.out.println(Collections.max(set));
        System.out.println(Collections.max(set, String.CASE_INSENSITIVE_ORDER));
    }
}
