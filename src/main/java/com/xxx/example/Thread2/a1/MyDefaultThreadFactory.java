package com.xxx.example.Thread2.a1;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DefaultThreadFactory 类提供了一种标准的方式来创建新线程，并且可以配置线程的名称、优先级、是否为守护线程等属性。
 * 它的主要作用是使得线程的创建更为灵活和可配置，同时也可以避免线程命名冲突等问题。
 *
 * Executors 是一个线程池工具类，提供了创建和管理线程池的方法:
 *       提供了各种类型的线程池，包括固定大小的线程池、可缓存的线程池、单线程池、计划任务线程池等。
 *       同时，它也提供了一些静态方法来创建 ExecutorService 等线程池对象，使得线程池的使用更为方便和简单。
 *       Executors 工具类还提供了其他一些有用的方法，例如：
 *          newSingleThreadExecutor()：创建一个只有一个线程的线程池。
 *          newCachedThreadPool()：创建一个可缓存线程的线程池。
 *          newScheduledThreadPool()：创建一个用于执行计划任务的线程池。
 *          newWorkStealingPool()：创建一个工作窃取线程池。
 */
public class MyDefaultThreadFactory {
    public static void main(String[] args) {
        DefaultThreadFactory factory = new DefaultThreadFactory("my-thread-pool", true);
        ExecutorService executor = Executors.newFixedThreadPool(10, factory);
        executor.submit(() -> {
            System.out.println("DefaultThreadFactory test!");
        });
        executor.shutdown();
    }
}
