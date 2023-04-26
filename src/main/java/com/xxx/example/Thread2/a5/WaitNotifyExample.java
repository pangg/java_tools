package com.xxx.example.Thread2.a5;

import java.util.ArrayList;
import java.util.List;

public class WaitNotifyExample {
    private static final Object lock = new Object();
    private static List<Integer> shareData = new ArrayList<>();
    private static final int MAX_SIZE = 10;
    private static boolean flag = false;

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();
    }

    static  class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                synchronized (lock) {
                    while (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    shareData.add(i);
                    System.out.println("Produced: " + i);
                    if (shareData.size() == MAX_SIZE) {
                        flag = true;
                    }
                    lock.notify();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (!flag && shareData.size() == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (shareData.size() > 0) {
                        int value = shareData.remove(0);
                        System.out.println("Consumed: " + value);
                    }
                    if (shareData.size() == 0) {
                        flag = false;
                    }
                    lock.notify();
                }
            }
        }
    }
}
