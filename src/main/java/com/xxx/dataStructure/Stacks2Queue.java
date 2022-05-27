package com.xxx.dataStructure;

/**
 * 栈实现队列
 *
 * 入队都在stack1中完成，出队都在stack2中完成，且遵循以下约束：
 *      入队：直接把元素压入stack1中；
 *      出队：若stack2中不为空，则直接弹出stack2中的元素；
 *          若stack2中为空，则将stack1中的所有元素倒入stack2中，然后弹出stack2的栈顶元素。
 *          同样，若两个栈都为空，则队列为空队，无法出队。
 */
public class Stacks2Queue {
    private final Stack stack1;
    private final Stack stack2;
    private final int maxLength;

    public Stacks2Queue(int capacity) {
        maxLength = capacity;
        stack1 = new Stack(capacity);
        stack2 = new Stack(capacity);
    }

    /**
     * 队列入队
     * @param element 入队元素
     * @return 入队结果
     */
    public boolean put(int element) {
        if (stack1.isFull() || maxLength == stack1.getSize()) {
            // 此时stack1满
            return false;
        }
        stack1.push(element);
        return true;
    }

    /**
     * 队列出队
     * @return 出队的元素
     */
    public int poll() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * 求队列长度
     * @return 返回队列长度
     */
    public int getSize() {
        return stack1.getSize() + stack2.getSize();
    }
}
