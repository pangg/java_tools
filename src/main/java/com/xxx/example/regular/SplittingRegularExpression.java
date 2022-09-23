package com.xxx.example.regular;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * 使用regex.Pattern类的Pattern.compile()方法和patternname.split()方法拆分正则表达式
 */
public class SplittingRegularExpression {
    public static void main(String[] args) {
        Pattern p = Pattern.compile(" ");
        String tmp = "this is the Java example";
        String[] tokens = p.split(tmp);

        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
    }

    @Test
    public void test2() {
        String s1 = "Learn how to use regular expression in Java programming. Here are most commonly used example";
        Pattern p1 = Pattern.compile("(to|Java|in|are|used)");
        String[] parts = p1.split(s1);

        for (String p : parts) {
            System.out.println(p);
        }
    }
}
