package com.xxx.dataStructure;

import org.junit.Test;

public class Stacks2QueueTest {
    @Test
    public void testDemo() {
        Stacks2Queue q = new Stacks2Queue(5);
        q.put(1);   //入队元素 1
        q.put(2);   //入队元素 2
        System.out.println("出队的元素为"+q.poll());   //出队元素   打印1
        System.out.println("此时队列长度为"+q.getSize());  //返回1
        q.put(3);   //入队元素 3
        q.put(4);   //入队元素 4
        System.out.println("出队的元素为"+q.poll());   //出队元素  打印2
        System.out.println("出队的元素为"+q.poll());   //出队元素  打印3 本次出队操作会把3和4两个元素从stack1中倒入stack2中
    }
}