package com.xxx.thread;

public class xkThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("run is " + this.isAlive());
    }

    public static void main(String[] args) {
        xkThread1 xkThread1 = new xkThread1();
        System.out.println("begin --- " + xkThread1.isAlive());
        xkThread1.start();
        System.out.println("end --- " + xkThread1.isAlive());
    }
}
