package com.xxx.thread.threadGroup;

public class ThreadGroupDestroyExp {
    public static void main(String arg[]) throws InterruptedException, SecurityException {
        // creating a ThreadGroup
        ThreadGroup g1 = new ThreadGroup("Parent thread");
        // creating a child ThreadGroup for parent ThreadGroup
        ThreadGroup g2 = new ThreadGroup(g1, "Child thread");

        // creating a thread
        NewThread t1 = new NewThread("Thread-1", g1);
        t1.start();
        // creating another thread
        NewThread t2 = new NewThread("Thread-2", g1);
        t2.start();

        // block until other thread is finished
        t1.join();
        t2.join();

        // destroying child threadGroup
        g2.destroy();
        System.out.println(g2.getName() + " destroyed");

        // destroying parent threadGroup
        g1.destroy();
        System.out.println(g1.getName() + " destroyed");
    }
}
