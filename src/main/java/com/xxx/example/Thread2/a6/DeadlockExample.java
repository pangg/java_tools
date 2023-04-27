package com.xxx.example.Thread2.a6;

/**
 * 线程安全问题中的活跃性问题:
 *  死锁（Deadlock）：两个或多个线程被无限期地阻塞，等待彼此持有的锁，以便继续执行。这会导致线程无法继续执行，造成程序停滞。
 *  活锁（Livelock）：两个或多个线程在等待彼此释放锁，但是没有任何线程能够继续执行。这种情况下，线程不断地重试，但是没有任何进展。
 *  饥饿（Starvation）：一个或多个线程无法获得执行所需的资源，例如CPU或内存，因为它们一直被其他线程占用。这会导致线程一直处于等待状态，无法继续执行。
 */
public class DeadlockExample {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 acquired lock 1");
                synchronized (lock2) {
                    System.out.println("Thread 1 acquired lock 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 acquired lock 2");
                synchronized (lock1) {
                    System.out.println("Thread 2 acquired lock 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
