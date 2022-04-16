package com.xxx.interview;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        primeNumber2();
        //System.out.println(randomInt2());
    }

    public static void primeNumber1() {
        long start = System.currentTimeMillis();
        for (int i = 2; i < 10000; i ++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    // 推荐效率更高
    public static void primeNumber2() {
        long start = System.currentTimeMillis();
        for (int i = 2; i < 10000; i++) {
            boolean isPrime = true;
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static Integer randomInt1() {
        int min = 30;
        int max = 100;
        //  Random().nextInt(upperLimit)
        return new Random().nextInt(max - min) + min;
    }

    public static int randomInt2() {
        int min = 30;
        int max = 100;
        // Math.random() 返回 0.0~1.0 之间的数
        return (int) (Math.random() * (max - min)) + min;
    }
}
