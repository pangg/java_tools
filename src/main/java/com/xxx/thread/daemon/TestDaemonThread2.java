package com.xxx.thread.daemon;

/**
 * 就绪的线程不能被设置为线程守护
 */
public class TestDaemonThread2 extends Thread{
    public void run() {
        System.out.println("Name: " + Thread.currentThread().getName());
        System.out.println("Daemon: " + Thread.currentThread().isDaemon());
    }

    public static void main(String[] args) {
        TestDaemonThread2 t1 = new TestDaemonThread2();
        TestDaemonThread2 t2 = new TestDaemonThread2();
        t1.start();
        t1.setDaemon(true);// will throw exception here
        t2.start();
    }
}
