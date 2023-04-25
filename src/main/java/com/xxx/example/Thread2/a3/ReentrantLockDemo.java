package com.xxx.example.Thread2.a3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 在关闭 ExecutorService 之前，我们先调用了 shutdown() 方法，然后使用 awaitTermination() 方法等待所有的任务执行完成或者超时。
 * 如果所有的任务在 10 秒钟内都执行完成，awaitTermination() 方法就会返回 true，否则返回 false。
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> counter.increment());
        }
        executorService.shutdown();

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getCount());
    }
}
