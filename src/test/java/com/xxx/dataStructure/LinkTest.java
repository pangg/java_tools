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

    @Test
    public void testLinkReverse() {
        Link link = new Link();
        link.add(0,1); //1
        link.add(1,2); //1->2
        link.add(2,3); //1->2->3
        link.add(3,4); //1->2->3->4
        link.add(4,5); //1->2->3->4->5
        link.printLink();//1->2->3->4->5
        link.linkReverse();
        link.printLink();//5->4->3->2->1
    }
}