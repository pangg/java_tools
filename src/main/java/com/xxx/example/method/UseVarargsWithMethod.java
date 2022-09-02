package com.xxx.example.method;

import org.junit.Test;

/**
 * 可变长度参数作为输入
 */
public class UseVarargsWithMethod {
    public int sunvarargs(int... intArrays) {
        int sum = 0;
        for (int i = 0; i < intArrays.length; i++) {
            sum += intArrays[i];
        }
        return sum;
    }

    @Test
    public void test1() {
        int sum = 0;
        sum = sunvarargs(11, 22, 33);
        System.out.println("The sum of the numbers is: " + sum);
    }

    public void display(String... values) {
        System.out.println("display method invoked ");
        for (String s : values) {
            System.out.println(s);
        }
    }

    @Test
    public void test2() {
        display();
        display("yiibai.com");
        display("my", "name", "is", "Sukia");
        display(new String[]{"a", "b", "c"});
    }
}
