package com.xxx.example.Thread2.a2;

/**
 * 用 interrupt 停止线程
 */
public class StopThread implements Runnable{

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 10000) {
            System.out.println("count = " + count++);
        }
        System.out.println("线程执行结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(2);
        thread.interrupt();
        Thread.sleep(2);
    }
}
