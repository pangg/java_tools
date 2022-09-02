package com.xxx.example.method;

import org.junit.Test;

/**
 * 计算斐波纳契系列的数字
 */
public class CalculatingFibonacciSeries {

    public long fibonacci(long number) {
        if ((number == 0) || (number == 1)) {
            return number;
        } else {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }

    @Test
    public void test1() {
        for (int counter = 0; counter < 15; counter ++) {
            System.out.printf("Fibonacci of %d is: %d\n", counter,
                    fibonacci(counter));
        }
    }

    @Test
    public void test2() {
        int count = 15;
        int[] feb = new int[count];
        feb[0] = 0;
        feb[1] = 1;
        for (int i = 2; i < count; i++) {
            feb[i] = feb[i-1] + feb[i-2];
        }

        for (int i = 0; i < count; i++) {
            System.out.print(feb[i] + " ");
        }
    }
}
