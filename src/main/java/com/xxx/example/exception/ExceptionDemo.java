package com.xxx.example.exception;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExceptionDemo {
    @Test
    public void exceptionMethods() {
        try {
            throw new Exception("My Exception");
        } catch (Exception e) {
            System.err.println("Caught Exception");
            System.err.println("getMessage():" + e.getMessage());
            System.err.println("getLocalizedMessage():" + e.getLocalizedMessage());
            System.err.println("toString():" + e);
            System.err.println("printStackTrace():");
            e.printStackTrace();
        }
    }

    /**
     * 使用Date类的System.currentTimeMillis()方法和Stack类的s.empty()，s.pop()方法来处理空堆栈异常
     */
    @Test
    public void emptyStackExcept() {
        int count = 1000000;
        Stack s = new Stack<>();
        System.out.println("Testing for empty stack");
        long s1 = System.currentTimeMillis();
        for (int i = 0; i <= count; i++)
            if (!s.empty())
                s.pop();
        long s2 = System.currentTimeMillis();
        System.out.println((s2 - s1) + " milliseconds");
        System.out.println("Catching EmptyStackException");
        s1 = System.currentTimeMillis();

        for (int i = 0; i <= count; i++) {
            try {
                s.pop();
            } catch (EmptyStackException e) {
            }
        }
        s2 = System.currentTimeMillis();
        System.out.println((s2 - s1) + " milliseconds");
    }

    /**
     * 使用多个catch块处理链异常
     */
    @Test
    public void chainedExceptions() {
        int n = 20, result = 0;
        try {
            result = n / 0;
            System.out.println("The result is" + result);
        } catch (ArithmeticException ex) {
            System.out.println("Arithmetic exception occoured: " + ex);
            ex.printStackTrace();
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException ex1) {
                System.out.println("Chained exception thrown manually : " + ex1);
                ex1.printStackTrace();
            }
        }
    }

    @Test
    public void printStackTrace2() {
        try {
            exceptionFunc();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void exceptionFunc() throws Throwable {
        Throwable t = new Throwable("This is new Exception in Java...");

        StackTraceElement[] trace = new StackTraceElement[] {
                new StackTraceElement("ClassName", "methodName", "fileName", 5) };
        t.setStackTrace(trace);
        throw t;
    }

    @Test
    public void exceptionWithThread() {
        MyThread t = new MyThread();
        t.start();
        try {
            Thread.sleep(1000);
        } catch (Exception x) {
            System.out.println("Caught it" + x);
            x.printStackTrace();
        }
        System.out.println("Exiting main");
    }

    class MyThread extends Thread {
        public void run() {
            System.out.println("Throwing in " + "MyThread");
            throw new RuntimeException();
        }
    }



}
