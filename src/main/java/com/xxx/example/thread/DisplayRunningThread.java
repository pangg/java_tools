package com.xxx.example.thread;

/**
 * 使用getName()方法显示所有正在运行的线程的名称
 */
public class DisplayRunningThread extends Thread {
    public static void main(String[] args) {
        DisplayRunningThread t1 = new DisplayRunningThread();
        t1.setName("thread1");
        t1.start();
        DisplayRunningThread t2 = new DisplayRunningThread();
        t2.setName("thread2");
        t2.start();
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);

        for (int i = 0; i < noThreads; i++){
            System.out.println("Thread No:" + i + " = " + lstThreads[i].getName());
        }
    }
}
