package com.xxx.counter;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CountDownLatch可以使当前线程阻塞，等待其他线程完成给定任务。可以类比旅游团导游要等待所有的游客到齐后才能去下一个景点。
 *      1. CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完 成，这里就传入N。
 *      这里所说的N个 点，可以是N个线程，也可以是1个线程里的N个执行步骤。CountDownLatch构造函数如下：
 *          public CountDownLatch(int count) {
 *              if (count < 0) throw new IllegalArgumentException("count < 0");
 *              this.sync = new Sync(count);
 *          }
 *      当我们调用CountDownLatch的countDown()方法时，N就会减1，CountDownLatch的await()方法 会阻塞当前线程，直到N变成零
 *
 *      2. CountDownLatch 方法
 *          await() 阻塞当前线程，直到计数器为零为止；
 *          await(long timeout, TimeUnit unit) await()的重载方法，可以指定阻塞时长；
 *          countDown() 计数器减1，如果计数达到零，释放所有等待的线程。
 *          getCount() 返回当前计数
 *
 *
 *      3. Thread.join()和CountDownLatch的区别
 *          Thread.join()是Thread类的一个方法，Thread.join()的实现是依靠Object的wait()和notifyAll()来完成的，而CountDownLatch是JUC包中的一个工具类。
 *          当我们使用ExecutorService ，就不能使用join，必须使用CountDownLatch比如：
 *
 *              ExecutorService service = Executors.newFixedThreadPool(5);
 *              final CountDownLatch latch = new CountDownLatch(5);
 *              for(int x = 0; x < 5; x++) {
 *                  service.submit(new Runnable() {
 *                       public void run() {
 *                          // do something
 *                          latch.countDown();
 *                      }
 *                  });
 *              }
 *              latch.await();
 *
 *          调用join方法需要等待thread执行完毕才能继续向下执行，而CountDownLatch只需要检查计数器的值为零就可以继续向下执行，相比之下，CountDownLatch更加灵活一些，可以实现一些更加复杂的业务场景。
 *
 */

// 使用多线程下载网络上图片，现在完成后，提示用户下载完成。
public class CountDownLatchDemo implements Runnable {

    private String url;
    private CountDownLatch countDownLatch;

    public CountDownLatchDemo(String url, CountDownLatch countDownLatch) {
        this.url = url;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        //省略无数业务代码
        System.out.println("线程" + Thread.currentThread().getName() + "开始下载完成");
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        // 使用Stream生成5个线程
        List<Thread> workers = Stream.generate(() -> new Thread(
                new CountDownLatchDemo("https://image.baidu.com/", countDownLatch)
        )).limit(5).collect(Collectors.toList());

        // 运行线程
        workers.forEach(Thread::start);

        // 等待线程完成
        countDownLatch.await();

        System.out.println("图片下载完成～");
    }
}
