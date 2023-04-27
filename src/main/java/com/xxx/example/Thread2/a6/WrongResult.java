package com.xxx.example.Thread2.a6;

/**
 * 线程安全:
 *      运行结果错误: i++ 操作 非线程安全
 */
public class WrongResult {
    volatile static int i;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        };

        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
