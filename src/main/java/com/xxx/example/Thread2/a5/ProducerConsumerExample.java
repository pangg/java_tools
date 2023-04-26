package com.xxx.example.Thread2.a5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue 是 Java 中的一个线程安全的队列，它提供了在多线程环境下安全地添加元素和移除元素的操作.
 *      1. 创建一个生产者线程，不断地向 BlockingQueue 中添加数据。
 *      可以使用 while 循环不断地生成数据，并通过 BlockingQueue 的 put() 方法将数据添加到队列中。如果队列已满，put() 方法将阻塞直到队列有空间。
 *      2. 创建一个消费者线程，不断地从 BlockingQueue 中取出数据进行处理。
 *      可以使用 while 循环不断地从队列中取出数据，并通过 BlockingQueue 的 take() 方法获取数据。如果队列为空，take() 方法将阻塞直到队列中有数据可取。
 */
public class ProducerConsumerExample {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        Thread producerThread = new Thread(() -> {
            while (true) {
                try {
                    String data = "data-" + System.currentTimeMillis();
                    queue.put(data);
                    System.out.println("Produced: " + data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                try {
                    String data = queue.take();
                    System.out.println("consumed: " + data);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
