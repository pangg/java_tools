package com.xxx.example.Thread2.a3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 是一个可重入的互斥锁，它可以替代 synchronized 关键字来实现线程同步。
 * 相比于 synchronized 关键字，ReentrantLock 提供了更加灵活的线程同步方式，可以更好地控制锁的获取和释放过程。
 * 需要注意的是，一定要在 finally 代码块中释放锁，以防止锁的死锁问题。
 */
public class Counter {
    private static int count = 0;

    private static final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        }finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            count --;
        } finally {
            lock.unlock();
        }
    }
}
