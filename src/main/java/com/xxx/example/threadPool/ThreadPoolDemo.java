package com.xxx.example.threadPool;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java常用的四种线程池
 *      1、CachedThreadPool - 可缓存线程池
 *      2、FixedThreadPool - 定长线程池
 *      3、SingleThreadExecutor - 单线程池
 *      4、ScheduledThreadPool - 调度线程池
 */
public class ThreadPoolDemo {
    /**
     * CachedThreadPool - 可缓存线程池
     *      特点：无限大，如果线程池中没有可用线程就会自动创建，有的话就自动利用起来。
     */
    @Test
    public void cachedThreadPool() {
        // Executors   调度器
        // ExecutorService  用于管理线程池
        ExecutorService executorService = Executors.newCachedThreadPool(); // 创建一个可缓存线程池
        for (int i = 1; i < 100; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + index);
                    try {
                        Thread.sleep((int)(Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // shutdown()   代表关闭线程池（等待所有线程执行完毕）
        // shutdownNow()  立即终止线程池，不等待线程执行完毕，不推荐使用
        executorService.shutdown();
    }

    /**
     * FixedThreadPool - 定长线程池
     *      特点是：固定线程总数，空闲线程用于执行任务。如果线程都在执行任务后续任务则处于等待状态，在线程池中的线程执行任务后再执行后续任务。
     *      如果线程处于等待状态，备选的等待算法默认为FIFO（先进先出），还有LIFO（后进先出）
     */
    @Test
    public void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(6); // 创建一个定长线程池
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + index);
                    try {
                        Thread.sleep((int)(Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    /**
     * SingleThreadExecutor - 单线程池
     */
    @Test
    public void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 创建一个单线程池
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + index);
                    try {
                        Thread.sleep((int)(Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        // 注意的是shutdown()方法必须要在awaitTermination()方法之前调用，该方法才会生效。否则会造成死锁。
        try {
            executorService.awaitTermination(1L, TimeUnit.DAYS);  // 超时时间1天
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ScheduledThreadPool - 调度线程池
     *      特点：可以根据设定的时间间隔执行任务。
     *          schedule（）设定的时间间隔执行一次；
     *          scheduleAtFixedRate（）设定的时间间隔重复执行。
     */
    @Test
    public void scheduleThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5); // 创建一个可调度线程池
        /*// 延迟3秒执行一次run方法
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟3秒执行~");
            }
        }, 3, TimeUnit.SECONDS);*/

        // 实际项目中，timer（类似时间间隔调度类）/newScheduledThreadPool都不会用到，有成熟的调度框架
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date() + "延迟1秒执行，每3秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
