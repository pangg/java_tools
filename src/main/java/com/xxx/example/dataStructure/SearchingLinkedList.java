package com.xxx.example.dataStructure;

import java.util.LinkedList;

/**
 * 使用linkedlistname.indexof(element)搜索链表中的元素获取元素的第一个位置，
 * 以及使用linkedlistname.Lastindexof(elementname)获取链表中元素的最后一个位置
 */
public class SearchingLinkedList {
    public static void main(String[] args) {
        LinkedList<String> lList = new LinkedList<String>();
        lList.add("11");
        lList.add("22");
        lList.add("33");
        lList.add("44");
        lList.add("66");
        lList.add("22");
        lList.add("12");

        System.out.println("First index of 22 is:" + lList.indexOf("22"));

        System.out.println("Last index of 22 is:" + lList.lastIndexOf("22"));
    }
}
