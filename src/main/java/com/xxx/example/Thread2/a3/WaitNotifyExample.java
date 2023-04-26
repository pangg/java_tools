package com.xxx.example.Thread2.a3;

/**
 * 使用wait()和notify()方法进行线程通信的示例
 */
public class WaitNotifyExample {
    public static void main(String[] args) {
        final Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Processor {
        private final Object lock = new Object();
        private volatile boolean isProduced = false;

        public void produce() throws InterruptedException {
            synchronized (lock) {
                System.out.println("Producer thread running ...");
                Thread.sleep(2000);
                isProduced = true;
                System.out.println("Producer thread is notifying ...");
                lock.notify();
            }
        }

        public void consume() throws InterruptedException {
            synchronized (lock) {
                while (!isProduced) {
                    System.out.println("Consumer thread is waiting ...");
                    lock.wait();
                }
                System.out.println("Consumer thread is consuming ...");
            }
        }
    }
}
