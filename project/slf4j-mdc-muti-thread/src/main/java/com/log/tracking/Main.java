package com.log.tracking;

import com.log.tracking.executor.MDCRunnable;
import com.log.tracking.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @See https://mp.weixin.qq.com/s/mHAlsC3h2xg_93KdFj3mOA
 * 如何快速过滤出一次请求的所有日志
 *
 * MDC工具，只要在接口或切面植入put()和remove()代码，在现网定位问题时，我们就可以通过grep requestId=xxx *.log快速的过滤出某次请求的所有日志。
 *
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        // 入口传入请求id
        MDCUtil.putRequestId();

        // 主线程打印日志
        logger.debug("log in main thread");

        /**
         * MDC之所以在异步线程中不生效是因为底层采用ThreadLocal作为数据结构，我们调用MDC.put()方法传入的请求ID只在当前线程有效。
         */
        // 异步线程打印日志，用MDCRunnable装饰Runnable
        new Thread(new MDCRunnable(new Runnable() {
            @Override
            public void run() {
                logger.debug("log in other thread");
            }
        })).start();

        // 异步线程池打印日志，用MDCRunnable装饰Runnable
        EXECUTOR.execute(new MDCRunnable(new Runnable() {
            @Override
            public void run() {
                logger.debug("log in other thread pool");
            }
        }));
        EXECUTOR.shutdown();

        // 出口移除请求ID
        MDCUtil.removeRequestId();
    }
}
