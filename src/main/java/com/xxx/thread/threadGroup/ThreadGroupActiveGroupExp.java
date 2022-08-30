package com.xxx.thread.threadGroup;

public class ThreadGroupActiveGroupExp {
    public static void main(String arg[]) {
        // creating the thread group
        ThreadGroup tg1 = new ThreadGroup("parent thread group");
        ThreadGroup tg2 = new ThreadGroup(tg1, "child threadGroup");
        ThreadGroup tg3 = new ThreadGroup(tg1, "child threadGroup");

        // creating the threads
        NewThread2 t1 = new NewThread2("Thread-1", tg1);
        System.out.println(t1.getName() + " starts");
        // this will call the run() method
        t1.start();

        // checking the number of active thread
        System.out.println("Number of active thread group: " + tg1.activeGroupCount());
    }
}

class NewThread2 extends Thread {
    NewThread2(String threadname, ThreadGroup tg) {
        super(tg, threadname);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
        System.out.println(Thread.currentThread().getName() + " completed executing");
    }
}
