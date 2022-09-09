package com.xxx.example.dataStructure;

import java.util.LinkedList;

/**
 * 使用clear()方法删除链表中的多个元素
 */
public class ClearLinkedListElements {
    public static void main(String[] args) {
        LinkedList<String> lList = new LinkedList<String>();
        lList.add("11");
        lList.add("22");
        lList.add("33");
        lList.add("44");
        lList.add("55");
        System.out.println(lList);
        lList.subList(2, 4).clear();
        System.out.println(lList);
    }
}
