package com.xxx.thread;

public class TestShutdown1 {
    public static void main(String[] args)throws Exception {

        Runtime r=Runtime.getRuntime();
        r.addShutdownHook(new MyThread());

        System.out.println("Now main sleeping... press ctrl+c to exit");
        try{
            Thread.sleep(3000);
        }catch (
                Exception e
        ) {}
    }
}

class MyThread extends Thread{
    public void run(){
        System.out.println("shut down hook task completed..");
    }
}
