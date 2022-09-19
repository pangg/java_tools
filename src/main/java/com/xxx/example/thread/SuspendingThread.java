package com.xxx.example.thread;

/**
 * 创建sleepThread()方法来暂停线程一段时间
 */
public class SuspendingThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SuspendingThread() {
        super("" + ++threadCount);
        start();
    }

    public String toString() {
        return "#" + getName() + ": " + countDown;
    }

    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++)
            new SuspendingThread().join();
        System.out.println("The thread has been suspened.");
    }
}
