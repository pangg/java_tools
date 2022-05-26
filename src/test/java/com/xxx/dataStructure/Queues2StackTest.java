package com.xxx.dataStructure;

import org.junit.Test;

public class Queues2StackTest {
    @Test
    public void testDemo() {
        Queues2Stack s = new Queues2Stack(5);
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.pop());  //返回3
        s.push(4);
        s.push(5);
        System.out.println(s.pop());  //返回5
        System.out.println(s.pop());  //返回4
        System.out.println(s.pop());  //返回2
        System.out.println(s.pop());  //返回1
        System.out.println(s.pop());  //抛出异常：提示栈为空
    }
}