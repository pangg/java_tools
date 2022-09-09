package com.xxx.example.dataStructure;

import java.util.LinkedList;

/**
 * 使用top()和pop()方法获取链表的元素
 */
public class GetLinkedListElements {
    private LinkedList list = new LinkedList();

    public void push(Object v) {
        list.addFirst(v);
    }

    public Object top() {
        return list.getFirst();
    }

    public Object pop() {
        return list.removeFirst();
    }

    public static void main(String[] args) {
        GetLinkedListElements stack = new GetLinkedListElements();
        for (int i = 10; i < 20; i++) {
            stack.push(new Integer(i));
        }

        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
