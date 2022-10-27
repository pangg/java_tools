package com.xxx.future;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Java中的Future模式
 *      cancel()：如果等太久，你可以直接取消这个任务
 *      isCancelled()：任务是不是已经取消了
 *      isDone()：任务是不是已经完成了
 *      get()：有2个get()方法，不带参数的表示无穷等待，或者你可以只等待给定时间
 *
 * @See https://zhuanlan.zhihu.com/p/364041672
 */
public class FutureDemo {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        //异步操作 可以用一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // 执行FutureTask，相当于上例中的 client.request("name") 发送请求
        // 在这里开启线程进行RealData的call()执行
        FutureTask<RealData> futureTask = new FutureTask<RealData>(new RealData("name"));
        executor.submit(futureTask);
        System.out.println(futureTask.get());
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Task task = new Task();
        Callable callable;
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(futureTask);
        System.out.println("sleep and wait~");

        Thread.sleep(10000);
        System.out.println(futureTask.get());
        executorService.shutdown();
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("开始计算任务");
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        return sum;
    }
}