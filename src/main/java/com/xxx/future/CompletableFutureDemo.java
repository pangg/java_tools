package com.xxx.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Future模式的高阶版本—— CompletableFuture
 *      Future模式虽然好用，但也有一个问题，那就是将任务提交给线程后，调用线程并不知道这个任务什么时候执行完，
 *      如果执行调用get()方法或者isDone()方法判断，可能会进行不必要的等待，那么系统的吞吐量很难提高。
 *
 * CompletableFuture 提供了一个回调机制，可以在任务完成后，自动回调一些后续的处理，这样，整个程序可以把“结果等待”完全给移除了.
 */

/**
 * 依赖关系
 *      thenApply()：把前面任务的执行结果，交给后面的Function
 *      thenCompose()：用来连接两个有依赖关系的任务，结果由第二个任务返回
 *
 * and集合关系
 *      thenCombine()：合并任务，有返回值
 *      thenAccepetBoth()：两个任务执行完成后，将结果交给thenAccepetBoth处理，无返回值
 *      runAfterBoth()：两个任务都执行完成后，执行下一步操作(Runnable类型任务)
 *
 * or聚合关系
 *      applyToEither()：两个任务哪个执行的快，就使用哪一个结果，有返回值
 *      acceptEither()：两个任务哪个执行的快，就消费哪一个结果，无返回值
 *      runAfterEither()：任意一个任务执行完成，进行下一步操作(Runnable类型任务)
 *
 * 并行执行
 *      allOf()：当所有给定的 CompletableFuture 完成时，返回一个新的 CompletableFuture
 *      anyOf()：当任何一个给定的CompletablFuture完成时，返回一个新的CompletableFuture
 *
 * 结果处理
 *      whenComplete：当任务完成时，将使用结果(或 null)和此阶段的异常(或 null如果没有)执行给定操作
 *      exceptionally：返回一个新的CompletableFuture，当前面的CompletableFuture完成时，它也完成，当它异常完成时，给定函数的异常触发这个CompletableFuture的完成
 *
 */
public class CompletableFutureDemo {

    /**
     * 某一个业务接口，需要处理几百个请求，请求之后再把这些结果给汇总起来。
     */
    public void test() {
        /*ExecutorService executor = Executors.newFixedThreadPool(5);

        List<CompletableFuture<Result>> futureList = requests
                .stream()
                .map(request->
                        CompletableFuture.supplyAsync(e->{
                            //some opts
                        },executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allCF = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));

        allCF.join();*/
    }


    public static void main(String[] args) throws InterruptedException {
        // 创建异步执行任务
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(CompletableFutureDemo::getPrice)
                // 当getPrice执行完成后，会自动回调thenAccept()中等函数
                .thenAccept(result -> {
                    System.out.println("price: " + result);
                })
                // 当出现异常时，会自动回调exceptionally()里的函数
                .exceptionally(e -> {
                    e.printStackTrace();
                    System.out.println("异常无结果");
                    return null;
                });

        future.join();
    }

    public static Double getPrice() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 模拟一个异常
        if (Math.random() < 0.3) {
            throw new RuntimeException("Error when get price");
        }
        return Math.random() * 20;
    }

}
