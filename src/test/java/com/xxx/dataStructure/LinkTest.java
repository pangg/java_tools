package com.xxx.dataStructure;

import org.junit.Test;

public class LinkTest {
    @Test
    public void testDemo() {
        Link link = new Link();
        link.addHead(1); //1
        link.printLink();

        link.addHead(5); //5->1
        link.printLink();

        link.addTail(9); //5->1->9
        link.printLink();

        link.addTail(7); //5->1->9->7
        link.printLink();

        link.add(3,8);  //5->1->9->8->7
        link.printLink();
        System.out.println("链表长度："+link.getLength()); //5

        link.deleFirst();  //1->9->8->7
        link.printLink();

        link.deleLast();  //1->9->8
        link.printLink();

        link.deleMid(1);  //1->8
        link.printLink();

        System.out.println("链表长度："+link.getLength()); //2
    }
}