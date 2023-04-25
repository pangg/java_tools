package com.xxx.example.Thread2.a1;

public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
