package com.xxx.future;

/**
 * 演示自定义future模式
 * @See https://zhuanlan.zhihu.com/p/364041672
 */
public class DefineFutureDemo {
    public static void main(String[] args) {
        Client client = new Client();
        // 这里会立即返回，因为得到的是FutureData而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            //这里可以用一个sleep代替了对其他业务逻辑的处理
            //在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用真实的数据，如果到这里数据还没有准备好，getResult()会等待数据准备完，再返回
        System.out.println("数据 = " + data.getResult());
    }
}
