package com.xxx.thread.threadGroup;

public class ThreadGroupListExp {
    public static void main(String arg[]) throws InterruptedException,
            SecurityException, Exception
    {
        // creating the thread group
        ThreadGroup tg1 = new ThreadGroup("Parent thread");
        ThreadGroup tg2 = new ThreadGroup(tg1, "Child thread");

        // creating a thread
        NewThread t1 = new NewThread("Thread-1", tg1);
        System.out.println(t1.getName() +" starts");
        t1.start();

        // creating an another thread
        NewThread t2 = new NewThread("Thread-2", tg1);
        System.out.println(t2.getName() +" starts");
        t2.start();

        // listing contents of parent ThreadGroup
        System.out.println("\\nListing parentThreadGroup: " + tg1.getName() + ":");
        // prints information about this thread group to the standard output
        tg1.list();
    }
}
