package com.xxx.example.regular;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用regex.Matcher类的matcher.groupCount()方法来计算字符串中的一组词组
 */
public class CountingGroupWords {
    public static void main(String args[]) {
        Pattern p = Pattern.compile("J(ava)");
        String candidateString = "This is Java. This is a Java example.";
        Matcher matcher = p.matcher(candidateString);
        int numberOfGroups = matcher.groupCount();
        System.out.println("numberOfGroups =" + numberOfGroups);
    }

    @Test
    public void test2() {
        Pattern p = Pattern.compile("(\\w+)%(\\d+)");
        Matcher m = p.matcher("ab%12-cd%34");
        System.out.println(m.groupCount());
    }

    @Test
    public void test3() {
        String s1 = "import java.util.regex.Pattern;importjava.util.regex.Matcher;";
        Pattern pattern = Pattern.compile("regex");
        Matcher  matcher = pattern.matcher(s1);
        int count = 0;

        while (matcher.find())count++;
        System.out.println(count);
    }
}
