package com.xxx.thread.threadGroup;


public class ThreadGroupCkeckAccessExp {
    public static void main(String[] args) {
        ThreadGroup g1 = new ThreadGroup("Parent thread");
        ThreadGroup g2 = new ThreadGroup("child thread");

        NewThread t1 = new NewThread("Thread-1", g1);
        t1.start();
        NewThread t2 = new NewThread("Thread-2", g1);
        t2.start();

        // Check for access permission of current running thread
        g1.checkAccess();
        System.out.println(g1.getName() + " has access");
        g2.checkAccess();
        System.out.println(g2.getName() + " has access");
    }
}

class NewThread extends Thread {
    public NewThread(String threadName, ThreadGroup tg) {
        super(tg, threadName);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
        System.out.println(Thread.currentThread().getName() + " completed executing");
    }
}
