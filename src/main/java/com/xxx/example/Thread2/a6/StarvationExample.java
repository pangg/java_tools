package com.xxx.example.Thread2.a6;

/**
 * 饥饿:
 * 有5个线程尝试获取锁并执行一个长时间运行的操作。由于只有一个锁，如果其中某个线程被其他线程一直占用，
 * 那么它将被阻塞，无法继续执行操作，直到该锁被释放。因此，该线程将一直处于饥饿状态，无法获得执行所需的资源。
 */
public class StarvationExample {
    private static Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " acquired lock");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
