package com.xxx.thread.join;

/**
 * 当t1完成其任务1500毫秒(3次)，然后t2和t3开始执行
 */
public class TestJoinMethod2 extends Thread{
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        TestJoinMethod2 t1 = new TestJoinMethod2();
        TestJoinMethod2 t2 = new TestJoinMethod2();
        TestJoinMethod2 t3 = new TestJoinMethod2();
        t1.start();
        try {
            t1.join(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        t2.start();
        t3.start();
    }
}
