package com.xxx.example.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 使用indexOfSubList()和lastIndexOfSubList()方法来检查列表中是否存在子列表，并查找列表中子列表的最后一次出现]
 */
public class FindSublist {
    public static void main(String[] args) {
        List list = Arrays.asList("one Two three Four five six one three Four".split(" "));
        System.out.println("List :" + list);

        List sublist = Arrays.asList("three Four".split(" "));
        System.out.println("SubList :" + sublist);
        System.out.println("indexOfSubList: " + Collections.indexOfSubList(list, sublist));

        System.out.println("lastIndexOfSubList: " + Collections.lastIndexOfSubList(list, sublist));
    }
}
