package com.xxx.thread.threadGroup;

public class ThreadGroupParentOfExp {
    public static void main(String arg[]) throws InterruptedException,
            SecurityException, Exception
    {
        // creating the thread group
        ThreadGroup g1 = new ThreadGroup("Parent thread");
        ThreadGroup g2 = new ThreadGroup(g1, "Child thread");

        // creating a thread
        NewThread t1 = new NewThread("Thread-1", g1);
        System.out.println(t1.getName()+" starts");
        t1.start();

        // creating another thread
        NewThread t2 = new NewThread("Thread-2", g1);
        System.out.println(t2.getName()+" starts");
        t2.start();

        // checking who is parent thread
        boolean isParent = g2.parentOf(g1);
        System.out.println(g2.getName() + " is the parent of " + g1.getName() +": "+ isParent);

        isParent = g1.parentOf(g2);
        System.out.println(g1.getName() + " is the parent of " + g2.getName() +": "+ isParent);
    }
}
