package com.xxx.dataStructure;

import org.junit.Test;

public class ArrayQueueTest {
    @Test
    public void demoTest() {
        ArrayQueue q = new ArrayQueue(4);   //初始化队列长度为3，因为循环队列有一个不放元素
        System.out.println(q.put("张三"));  //入队，true
        System.out.println(q.put("李四"));  //入队，true
        System.out.println(q.put("赵五"));  //入队，true
        System.out.println(q.put("老王"));  //队满，false

        System.out.println(q.isFull());  //队满 true
        System.out.println(q.getSize()); //3，队列中有3个元素

        System.out.println(q.peak());  //返回“张三”  不出队
        System.out.println(q.pull());  //返回“张三”
        System.out.println(q.pull());  //返回“李四”
        System.out.println(q.pull());  //返回“赵五”

        System.out.println(q.isEmpty());  //true 空队
        System.out.println(q.put("张三1"));
        System.out.println(q.put("张三2"));
        System.out.println(q.pull());
    }
}