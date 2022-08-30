package com.xxx.thread.threadGroup;

public class ThreadGroupGetNameExp {
    public static void main(String arg[]) throws InterruptedException, SecurityException, Exception {
        // creating the threadGroup
        ThreadGroup tg1 = new ThreadGroup("Parent thread");
        ThreadGroup tg2 = new ThreadGroup(tg1, "Child thread");

        // creating a thread
        NewThread3 t1 = new NewThread3("Thread-1", tg1);
        System.out.println("First threadGroup's name: " + t1.getThreadGroup().getName());

        // creating another thread
        NewThread3 t2 = new NewThread3("Thread-2", tg2);
        System.out.println("Second threadGroup's name: " + t2.getThreadGroup().getName());
    }
}

class NewThread3 extends Thread {
    NewThread3(String threadname, ThreadGroup tg) {
        super(tg, threadname);
        start();
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
}