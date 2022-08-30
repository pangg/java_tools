package com.xxx.thread.create;

/**
 * 通过扩展Thread类线程示例
 */
public class ThreadDemo extends Thread{
    public void run() {
        System.out.println("thread is running...");
    }

    public static void main(String[] args) {
        ThreadDemo t1 = new ThreadDemo();
        t1.start();
    }
}
