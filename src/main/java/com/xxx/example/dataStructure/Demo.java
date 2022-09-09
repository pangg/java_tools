package com.xxx.example.dataStructure;

import org.junit.Test;

import java.util.LinkedList;

public class Demo {
    /**
     * 获取链表的第一个和最后一个元素
     */
    @Test
    public void firstAndLastElement() {
        LinkedList<String> lList = new LinkedList<String>();
        lList.add("111");
        lList.add("222");
        lList.add("333");
        lList.add("444");
        lList.add("555");
        lList.add("999");
        System.out.println("First element of LinkedList is : " + lList.getFirst());
        System.out.println("Last element of LinkedList is : " + lList.getLast());
    }

    /**
     * 在链表的第一个和最后一个位置添加一个元素
     */
    @Test
    public void addingElement2LinkedList() {
        LinkedList<String> lList = new LinkedList<String>();
        lList.add("1");
        lList.add("2");
        lList.add("3");
        lList.add("4");
        lList.add("5");
        System.out.println(lList);

        lList.addFirst("0");
        System.out.println(lList);

        lList.addLast("99");
        System.out.println(lList);
    }


}
