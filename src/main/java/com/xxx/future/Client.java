package com.xxx.future;

public class Client {
    // 这是一个异步方法，返回的Data接口是一个Future
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            @Override
            public void run() {
                // RealData 的构建很慢，所以在单独的线程中进行
                RealData realData = new RealData(queryStr);
                // setRealData()的时候notify()等待在这个future上的对象
                future.setRealData(realData);
            }
        }.start();
        // FutureData会被立即返回，不会等待RealData被构造完
        return future;
    }
}
