package com.xxx.example.stopWatch;

import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * StopWatch对象不是设计为线程安全的，并且不使用同步。
 * 一个StopWatch实例一次只能开启一个task，不能同时start多个task
 * 在该task还没stop之前不能start一个新的task，必须在该task stop之后才能开启新的task
 * 若要一次开启多个，需要new不同的StopWatch实例
 */
public class SpringStopWatchDemo {
    @Test
    public void test2() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        // 任务一模拟休眠3秒钟
        stopWatch.start("TaskOneName");
        Thread.sleep(1000 * 3);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 任务一模拟休眠10秒钟
        stopWatch.start("TaskTwoName");
        Thread.sleep(1000 * 2);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 任务一模拟休眠10秒钟
        stopWatch.start("TaskThreeName");
        Thread.sleep(1000 * 3);
        System.out.println("当前任务名称：" + stopWatch.currentTaskName());
        stopWatch.stop();

        // 打印出耗时
        System.out.println(stopWatch.prettyPrint());
        System.out.println("-----------1-----------");
        System.out.println(stopWatch.shortSummary());
        // stop后它的值为null
        System.out.println("-----------2-----------");
        System.out.println(stopWatch.currentTaskName());

        // 最后一个任务的相关信息
        System.out.println("-----------3-----------");
        System.out.println(stopWatch.getLastTaskName());
        System.out.println("-----------4-----------");
        System.out.println(stopWatch.getLastTaskInfo());

        // 任务总的耗时  如果你想获取到每个任务详情（包括它的任务名、耗时等等）可使用
        System.out.println("所有任务总耗时：" + stopWatch.getTotalTimeMillis());
        System.out.println("任务总数：" + stopWatch.getTaskCount());
        System.out.println("所有任务详情：" + stopWatch.getTaskInfo());
    }

    @Test
    public void test4() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("任务耗时秒表工具");

        stopWatch.start("task1");
        Thread.sleep(1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        stopWatch.start("task2");
        Thread.sleep(3000);
        stopWatch.stop();
        //所有任务耗时时间
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());

        StopWatch stopWatch2 = new StopWatch("任务耗时秒表工具2");
        stopWatch2.start("task3");
        Thread.sleep(3000);
        stopWatch2.stop();
        //所有任务耗时时间
        System.out.println(stopWatch2.getTotalTimeMillis());
        System.out.println(stopWatch2.prettyPrint());
    }
}
