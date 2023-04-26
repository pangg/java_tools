package com.xxx.example.Thread2.a5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final Queue<String> queue = new LinkedList<>();
    private final int capacity = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(String data) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.offer(data);
            System.out.println("Produced: " + data);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            String data = queue.poll();
            System.out.println("Consumed: " + data);
            notFull.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();

        Thread producerThread = new Thread(() -> {
            try {
                while (true) {
                    example.produce("data-" + System.currentTimeMillis());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    example.consume();
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
