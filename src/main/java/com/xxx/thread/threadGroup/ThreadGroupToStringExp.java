package com.xxx.thread.threadGroup;

public class ThreadGroupToStringExp {
    public static void main(String arg[])
    {
        // creating the thread group
        ThreadGroup tg1 = new ThreadGroup("parent thread group");
        ThreadGroup tg2 = new ThreadGroup(tg1, "child threadGroup");

        // creating a thread
        NewThread t1 = new NewThread("Thread-1", tg1);
        System.out.println(t1.getName() + " starts");
        t1.start();

        // creating another thread
        NewThread t2 = new NewThread("Thread-2", tg1);
        System.out.println(t2.getName() + " starts");
        t2.start();

        // string equivalent of the parent group
        System.out.println("String equivalent: "
                + tg1.toString());

        // string equivalent of the child group
        System.out.println("String equivalent: "
                + tg2.toString());
    }
}
