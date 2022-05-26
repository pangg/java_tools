package com.xxx.dataStructure;

import java.util.Arrays;

/**
 * 栈是一种有着特殊操作规则的数据结构——后进先出；
 * 栈的特点显而易见，只能从一端进行元素的压入和弹出，先进去的后出来。
 * 应用场景：逆序输出；编译器语法检测等。
 */
public class Stack {
    private int size = 0; // 栈顶位置
    private int[] array;

    public Stack() {
        this(10);
    }

    public Stack(int init) {
        if (init <= 0) {
            init = 10;
        }
        array = new int[init];
    }

    /**
     * 入栈操作
     * @param item 入栈元素
     */
    public void push(int item) {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);  // 扩容操作
        }
        array[size++] = item;
    }

    /**
     * 获取栈顶元素，但栈顶元素不出栈
     * @return
     */
    public int peek() {
        if (size == 0) { // 空栈
            throw new IndexOutOfBoundsException("栈是空的");
        }
        return array[size - 1];
    }

    /**
     * 出栈，同时获取栈顶元素
     * @return
     */
    public int pop() {
        int item = peek();  // 获取栈顶元素
        size --;  // 直接使元素个数减1，不用清除元素，下次入栈会覆盖旧元素的值
        return item;
    }

    /**
     * 判断栈是否已满
     * @return
     */
    public boolean isFull() {
        return size == array.length;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
