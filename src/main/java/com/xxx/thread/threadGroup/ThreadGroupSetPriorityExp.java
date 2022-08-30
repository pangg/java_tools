package com.xxx.thread.threadGroup;

public class ThreadGroupSetPriorityExp {
    public static void main(String arg[]) throws InterruptedException, SecurityException, Exception {
        // creating the thread group
        ThreadGroup tg1 = new ThreadGroup("Parent threadGroup");
        ThreadGroup tg2 = new ThreadGroup(tg1, "child threadGroup");
        tg1.setMaxPriority(Thread.MAX_PRIORITY-3);
        tg2.setMaxPriority(Thread.MIN_PRIORITY);

        // creating a thread
        NewThread5 t1 = new NewThread5("Thread-1", tg1);
        t1.setPriority(Thread.MAX_PRIORITY);
        System.out.println(t1.getName() + " starts");
        t1.start();

        // creating another thread
        NewThread5 t2 = new NewThread5("Thread-2", tg2);
        t2.setPriority(Thread.MAX_PRIORITY);
        System.out.println(t2.getName() + " starts");
        t2.start();
    }
}

class NewThread5 extends Thread
{
    NewThread5(String threadname, ThreadGroup tgob)
    {
        super(tgob, threadname);
    }
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException ex) {
                System.out.println("Thread " + Thread.currentThread().getName() + " interrupted"); }
        }
        System.out.println(Thread.currentThread().getName() + " [priority = " +
                Thread.currentThread().getPriority() + "]");
        System.out.println(Thread.currentThread().getName()+" finished execution");
    }
}
