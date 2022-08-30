package com.xxx.thread;

/**
 * 如果直接调用run()方法而不是start()方法会怎么样？
 * 每个线程在一个单独的调用堆栈中启动。从主线程调用run()方法，run()方法进入当前调用堆栈而不是新调用堆栈的开头。
 */
public class TestCallRun2 extends Thread {
    public void run() {
        for (int i = 1; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        TestCallRun2 t1 = new TestCallRun2();
        TestCallRun2 t2 = new TestCallRun2();

        t1.run();
        t2.run();
    }
}
