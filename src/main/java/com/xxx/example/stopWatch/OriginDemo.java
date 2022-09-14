package com.xxx.example.stopWatch;

import org.junit.Test;

public class OriginDemo {
    @Test
    public void test1() {
        Long startTime = System.currentTimeMillis();
        // TODO something...
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        Long elapsedTime = (endTime - startTime) / 1000;
        System.out.println("该段总共耗时：" + elapsedTime + "s");
    }
}
