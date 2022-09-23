package com.xxx.example.regular;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用Pattern类Pattern.compile()方法和Matcher类的m.find()方法来重置正则表达式的模式
 */
public class SplittingString {
    public static void main(String[] args) throws Exception {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (m.find())
            System.out.println(m.group());
        System.out.println("----------------");
        m.reset("fix the rig with rags");
        while (m.find())
            System.out.println(m.group());
    }

    @Test
    public void test2() {
        Pattern p = Pattern.compile("\\d");
        Matcher mat1 = p.matcher("9652018244");

        while (mat1.find()) {
            System.out.println("\t" + mat1.group());
        }
        mat1.reset("123");
        System.out.println("After done resetting the Matcher, it should be like this");

        while (mat1.find()) {
            System.out.println("\t" + mat1.group());
        }
    }
}
