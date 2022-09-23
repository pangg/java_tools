package com.xxx.example.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用Matcher类的replaceFirst()方法替换字符中指定的子字符串的首次出现
 */
public class ReplaceFirstOccurrence {
    public static void main(String args[]) {
        Pattern p = Pattern.compile("hello");
        String instring = "hello, hello word.";
        System.out.println("initial String: " + instring);
        Matcher m = p.matcher(instring);
        String tmp = m.replaceFirst("Java");
        System.out.println("String after replacing 1st Match: " + tmp);
    }
}
