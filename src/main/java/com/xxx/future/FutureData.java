package com.xxx.future;

public class FutureData implements Data{
    // 内部需要维护RealData
    protected RealData realData = null;
    protected Boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        // RealData已经被注入，通知getResult()
        notifyAll();
    }

    // 会等待RealData构造完成
    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                // 一直等待，直到RealData被注入
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 真正需要的数据从RealData获取
        return realData.result;
    }
}
