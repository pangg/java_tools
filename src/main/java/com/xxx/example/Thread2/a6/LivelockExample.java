package com.xxx.example.Thread2.a6;

/**
 * 活锁（Livelock）：两个或多个线程在等待彼此释放锁，但是没有任何线程能够继续执行。这种情况下，线程不断地重试，但是没有任何进展。
 */
public class LivelockExample {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    private static boolean moveLeft = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (moveLeft) {
                    synchronized (lock1) {
                        System.out.println("Thread 1 moved left and acquired lock 1");
                        moveLeft = false;
                    }
                } else {
                    System.out.println("Thread 1 released lock 1");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if (!moveLeft) {
                    synchronized (lock2) {
                        System.out.println("Thread 2 moved right and acquired lock 2");
                        moveLeft = true;
                    }
                } else {
                    System.out.println("Thread 2 released lock 2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
