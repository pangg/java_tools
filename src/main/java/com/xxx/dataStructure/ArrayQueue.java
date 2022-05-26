package com.xxx.dataStructure;

/**
 * 队列：队首(head)和队尾(tail)以及队列长度；
 * 先进先出
 * 顺序队列的假溢：
 *      上溢： 当队列中无法插入元素时，称之为上溢；
 *      假上溢： 在顺序队列中，数组还有空间（不一定全空）但无法入队称之为假上溢；
 *      真上溢： 如果head为0，tail指向数组之外，即数组真满了，称之为真上溢；
 *      下溢： 如果空队中执行出队操作，此时队列中无元素，称之为下溢
 *
 *      顺序队列存在“假上溢”的现象，所以一般用循环队列实现。
 *      在采用循环队列实现的过程中，当队列满队时，tail等于head，而当队列空时，tail也等于head，
 *   为了区分两种状态，一般规定循环队列的长度为数组长度-1，即有一个位置不放元素，此时head==tail时为空队，
 *   而当head==(tail+1)%数组长度，说明对满。
 *
 */
public class ArrayQueue {
    private final Object[] queue;  // 声明一个数组
    private int head;
    private int tail;

    /**
     * 初始化队列
     * @param capacity 队列长度
     */
    public ArrayQueue(int capacity) {
        this.queue = new Object[capacity];
    }

    /**
     * 入队
     * @param o 入队元素
     * @return 入队是否成功
     */
    public boolean put(Object o) {
        if (head == (tail + 1) % queue.length) {
            // 队列已满
            return false;
        }
        queue[tail] = o;
        tail = (tail+1) % queue.length;  // tail标记后移一位
        return true;
    }

    /**
     * 返回首元素，但不出队
     * @return
     */
    public Object peak() {
        if (head == tail) {
            // 空队列
            return null;
        }
        return queue[head];
    }

    /**
     * 出队
     * @return 出队元素
     */
    public Object pull() {
        if (head == tail) {
            return null;
        }
        Object item = queue[head];
        queue[head] = null;
        head = (head+1) % queue.length;
        return item;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 判断队列是否为满
     * @return
     */
    public boolean isFull() {
        return head == (tail+1) % queue.length;
    }

    /**
     * 获取队列中元素个数
     * @return
     */
    public int getSize() {
        if (tail >= head) {
            return tail - head;
        } else {
            return (tail + queue.length) - head;
        }
    }
}
