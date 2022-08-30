package com.xxx.thread;

/**
 * 每个线程都有一个优先级。优先级是由1到10之间的数字表示。
 * 线程的默认优先级为5(NORM_PRIORITY)。 MIN_PRIORITY的值为1，MAX_PRIORITY的值为10。
 */
public class TestMultiPriority1 extends Thread {
    @Override
    public void run() {
        System.out.println("running thread name is:" + Thread.currentThread().getName());
        System.out.println("running thread priority is:" + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        TestMultiPriority1 m1 = new TestMultiPriority1();
        TestMultiPriority1 m2 = new TestMultiPriority1();
        m1.setPriority(Thread.MIN_PRIORITY);
        m2.setPriority(Thread.MAX_PRIORITY);
        m1.start();
        m2.start();
    }
}
