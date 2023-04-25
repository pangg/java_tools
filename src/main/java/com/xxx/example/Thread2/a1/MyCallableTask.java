package com.xxx.example.Thread2.a1;

import java.util.concurrent.*;

/**
 * Callable 是一个接口，它允许在一个线程中执行一个任务，并返回一个结果;
 * Callable 接口通常与 ExecutorService 结合使用，用于在线程池中执行任务并获取结果。
 * ExecutorService.submit(Callable) 方法可以用于向线程池提交一个 Callable 任务，并返回一个 Future 对象，该对象可以用于获取任务的执行结果。
 */
public class MyCallableTask implements Callable<String> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return "Task Completed";
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<String> future = executor.submit(new MyCallableTask());
        String result = future.get();
        System.out.println(result);
        executor.shutdown();
    }
}
