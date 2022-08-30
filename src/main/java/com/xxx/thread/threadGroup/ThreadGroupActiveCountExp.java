package com.xxx.thread.threadGroup;

public class ThreadGroupActiveCountExp {
    public static void main(String arg[]) {
        // creating the thread group
        ThreadGroup tg1 = new ThreadGroup("parent thread group");

        // creating the threads
        NewThread1 t1 = new NewThread1("Thread-1", tg1);
        NewThread1 t2 = new NewThread1("Thread-2", tg1);
        NewThread1 t3 = new NewThread1("Thread-3", tg1);

        // this will call the run() method
        t1.start();
        t2.start();
        t3.start();

        // checking the number of active thread
        System.out.println("Number of active thread: " + tg1.activeCount());
    }
}

class NewThread1 extends Thread {
    NewThread1(String threadname, ThreadGroup tg) {
        super(tg, threadname);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
        System.out.println(Thread.currentThread().getName() + " completed executing");
    }
}