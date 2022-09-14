package com.xxx.example.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * 使用CollectionIlator()和Collection.List()的Collection.reverse()方法来反转集合
 */
public class ReversingCollection {
    public static void main(String[] args) {
        String[] coins = { "A", "B", "C", "D", "E" };
        List<String> l = new ArrayList<>();

        for (int i = 0; i < coins.length; i++)
            l.add(coins[i]);
        ListIterator liter = l.listIterator();
        System.out.println("Before reversal");
        while (liter.hasNext())
            System.out.println(liter.next());

        Collections.reverse(l);

        liter = l.listIterator();
        System.out.println("After reversal");
        while (liter.hasNext())
            System.out.println(liter.next());
    }
}
