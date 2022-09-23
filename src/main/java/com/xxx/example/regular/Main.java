package com.xxx.example.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用Pattern.compile()方法和m.group()方法找到一个词出现次数
 */
public class Main {
    public static void main(String args[])
            throws Exception {
        String candidate = "this is a test, A TEST.";
        String regex = "\\ba\\w*\\b";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(candidate);
        String val = null;
        System.out.println("INPUT: " + candidate);
        System.out.println("REGEX: " + regex + " ");
        while (m.find()) {
            val = m.group();
            System.out.println("MATCH: " + val);
        }
        if (val == null) {
            System.out.println("NO MATCHES: ");
        }
    }
}
