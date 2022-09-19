package com.xxx.example.thread;

/**
 * 使用setPriority()方法打印正在运行的线程的优先
 */
public class GettingThreadPriority extends Thread {
    private int countDown = 5;
    private volatile double d = 0;

    public GettingThreadPriority(int priority) {
        setPriority(priority);
        start();
    }

    public String toString() {
        return super.toString() + ": " + countDown;
    }

    public void run() {
        while (true) {
            for (int i = 1; i < 100000; i++)
                d = d + (Math.PI + Math.E) / (double) i;
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        new GettingThreadPriority(Thread.MAX_PRIORITY);
        for (int i = 0; i < 5; i++) {
            new GettingThreadPriority(Thread.MIN_PRIORITY);
        }
    }
}
