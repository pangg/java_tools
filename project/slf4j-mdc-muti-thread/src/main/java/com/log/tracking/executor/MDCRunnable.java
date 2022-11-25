package com.log.tracking.executor;

import com.log.tracking.util.MDCUtil;

import java.util.Map;

public class MDCRunnable implements Runnable {

    private final Runnable runnable;

    private final Map<String, String> map;

    public MDCRunnable(Runnable runnable) {
        this.runnable = runnable;
        // 保存当前线程的MDC值
        this.map = MDCUtil.getCopyOfContextMap();
    }

    @Override
    public void run() {
        // 传入已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDCUtil.put(entry.getKey(), entry.getValue());
        }

        // 装饰器模式，执行run方法
        runnable.run();
        // 移除已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDCUtil.remove(entry.getKey());
        }

    }
}
