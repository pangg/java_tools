package com.xxx.thread.threadGroup;

public class ThreadGroupGetParentExp {
    public static void main(String arg[]) throws InterruptedException,
            SecurityException, Exception
    {
        // creating a parent threadGroup
        ThreadGroup tg1 = new ThreadGroup("Parent thread");
        // creating a child threadGroup
        ThreadGroup tg2 = new ThreadGroup(tg1, "Child thread");

        // creating a thread
        NewThread t1 = new NewThread("Thread-1", tg1);
        System.out.println("Starting Thread-1");
        t1.start();

        // creating another thread
        NewThread t2 = new NewThread("Thread-2", tg2);
        System.out.println("Starting Thread-2");
        t2.start();

        // print the parentThreadGroup of parent thread
        System.out.println("ParentThreadGroup for " +
                tg1.getName() + " is " + tg1.getParent().getName());
        // print the parentThreadGroup of child thread
        System.out.println("ParentThreadGroup for " +
                tg2.getName() + " is " + tg2.getParent().getName());
    }

}
