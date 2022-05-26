package com.xxx.dataStructure;

import org.junit.Test;

public class StackTest {
    @Test
    public void demo() {
        Stack s = new Stack(3);
        s.push(2);
        s.push(5);
        s.push(7);
        s.push(10);  // 此时扩容，自底向顶 2—>5->7->10

        System.out.println("栈顶位置是："+s.getSize()); //栈顶指针为4，数组长度为3*2=6
        System.out.println("获取栈顶元素："+s.peek());  //获取但不弹栈
        System.out.println("弹出栈顶元素："+s.pop());//弹栈返回10,自底向顶 2—>5->7
        System.out.println("弹出栈顶元素："+s.pop());//弹栈返回7,自底向顶 2—>5
        s.push(66);   //入栈后，自底向顶 2—>5->66
        System.out.println("弹出栈顶元素："+s.pop());//弹栈返回66
        System.out.println("弹出栈顶元素："+s.pop());//弹栈返回5
        System.out.println("弹出栈顶元素："+s.pop());//弹栈返回2
        System.out.println("弹出栈顶元素："+s.pop());//抛出异常，提示为空栈
    }

}