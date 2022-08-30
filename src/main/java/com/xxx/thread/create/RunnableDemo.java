package com.xxx.thread.create;

public class RunnableDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("thread is running...");
    }

    public static void main(String[] args) {
        RunnableDemo m1 = new RunnableDemo();
        Thread t1 = new Thread(m1);
        t1.start();
    }
}
