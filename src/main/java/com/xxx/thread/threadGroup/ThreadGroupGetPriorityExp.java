package com.xxx.thread.threadGroup;

public class ThreadGroupGetPriorityExp {
    public static void main(String arg[]) throws InterruptedException, SecurityException, Exception {
        // creating a parent threadGroup
        ThreadGroup tg1 = new ThreadGroup("Parent thread");
        // returns the maximum priority of parent thread
        System.out.println("Maximum priority of Parent threadGroup: " + tg1.getMaxPriority());

        // creating a child threadGroup
        ThreadGroup tg2 = new ThreadGroup(tg1, "Child thread");
        // returns the maximum priority of child thread
        System.out.println("Maximum priority of Child threadGroup: " + tg1.getMaxPriority());

        // creating a thread
        NewThread t1 = new NewThread("Thread-1", tg1);
        t1.start();

        // creating another thread
        NewThread t2 = new NewThread("Thread-2", tg2);
        t2.start();
    }
}
