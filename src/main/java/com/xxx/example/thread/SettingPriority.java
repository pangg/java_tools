package com.xxx.example.thread;

/**
 * 使用setPriority()方法来设置线程的优先级
 */
public class SettingPriority {
    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        Thread thread3 = new Thread();
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("The priority has been set. Now get priority : ");
        System.out.println("thread1 priority = " + thread1.getPriority());
        System.out.println("thread2 priority = " + thread2.getPriority());
        System.out.println("thread3 priority = " + thread3.getPriority());
    }
}
