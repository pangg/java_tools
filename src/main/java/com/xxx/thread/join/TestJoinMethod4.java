package com.xxx.thread.join;

/**
 * currentThread()方法的示例
 */
public class TestJoinMethod4 extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        TestJoinMethod4 t1 = new TestJoinMethod4();
        TestJoinMethod4 t2 = new TestJoinMethod4();

        t1.start();
        t2.start();
    }
}
