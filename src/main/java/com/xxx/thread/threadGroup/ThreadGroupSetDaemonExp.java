package com.xxx.thread.threadGroup;

public class ThreadGroupSetDaemonExp {
    public static void main(String arg[]) throws InterruptedException, SecurityException, Exception {
        // creating a parent threadGroup
        ThreadGroup tg1 = new ThreadGroup("Parent thread");
        tg1.setDaemon(true);

        // creating a child threadGroup
        ThreadGroup tg2 = new ThreadGroup(tg1, "Child thread");
        tg2.setDaemon(false);

        // creating a thread
        NewThread t1 = new NewThread("Thread-1", tg1);
        t1.start();

        // creating another thread
        NewThread t2 = new NewThread("Thread-2", tg2);
        t2.start();

        // returns true if this thread group is a daemon thread group
        System.out.println("Is " + tg1.getName() + " a daemon threadGroup? " + tg1.isDaemon());
        System.out.println("Is " + tg2.getName() + " a daemon threadGroup? " + tg2.isDaemon());
    }
}
