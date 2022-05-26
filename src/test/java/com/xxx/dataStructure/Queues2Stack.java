package com.xxx.dataStructure;

/**
 * 队列实现栈的方案:
 * 准备两个队列queue1和queue2；
 * 入栈：
 *      两个队列全空：任选一个队列让元素入队，此处规定queue1
 *      两个队列一个空：让元素入队非空的队列
 * 注：不考虑两个队列全满，因为本身没意义
 * 出栈： 将非空队列中除最后入队的元素之外的其他所有元素入队到另外一个队列中，然后出队剩下的那个元素（后进来的先出去，完成出栈）
 */
public class Queues2Stack {
    private ArrayQueue q1;
    private ArrayQueue q2;
    private int maxLength;

    public Queues2Stack(int capacity) {
        maxLength = capacity;
        q1 = new ArrayQueue(capacity);
        q2 = new ArrayQueue(capacity);
    }

    public int getSize() {
        return q1.getSize() + q2.getSize();
    }

    /**
     * 入栈
     * @param element 入栈元素
     * @return
     */
    public boolean push(int element) {
        if (getSize() == maxLength) {  // 队列都满，此情景无意义
            return false;
        }
        if (q2.isEmpty()) {
            q1.put(element);
        } else {
            q2.put(element);
        }
        return true;
    }

    /**
     * 出栈
     * @return 出栈元素
     */
    public Object pop() {
        if (getSize() == 0) {
            throw new IndexOutOfBoundsException("空栈，无元素可出栈");
        } else {
            if (q2.isEmpty()) {
                while (q1.getSize() > 1) {
                    q2.put(q1.pull());
                }
                return q1.pull(); //出队最后一个，实现后进先出
            } else {
                while (q2.getSize() > 1) {
                    q1.put(q2.pull());
                }
                return q2.pull();  //出队最后一个，实现后进先出
            }
        }
    }
}
