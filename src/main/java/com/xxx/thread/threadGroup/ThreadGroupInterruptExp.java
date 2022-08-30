package com.xxx.thread.threadGroup;

public class ThreadGroupInterruptExp {
    public static void main(String arg[]) throws InterruptedException, SecurityException {
        // creating the thread group
        ThreadGroup tg1 = new ThreadGroup("Parent thread");
        ThreadGroup tg2 = new ThreadGroup(tg1, "child thread");

        // creating a thread
        NewThread4 t1 = new NewThread4("Thread-1", tg1);
        System.out.println(t1.getName() + " starts");
        t1.start();

        // interrupting thread group
        tg1.interrupt();

        // creating another thread
        NewThread4 t2 = new NewThread4("Thread-2", tg1);
        System.out.println(t2.getName() + " starts");
        t2.start();
    }

}

class NewThread4 extends Thread
{
    NewThread4(String threadname, ThreadGroup tg)
    {
        super(tg, threadname);
    }
    public void run()
    {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName()
                        + " interrupted");
            }
        }
        System.out.println(Thread.currentThread().getName() +
                " completed executing");
    }
}