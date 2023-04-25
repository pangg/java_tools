package com.xxx.example.Thread2.a1;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("用实现Runnable接口实现线程");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
