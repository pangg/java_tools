package com.xxx.example.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用Matcher类的replaceAll()方法替换字符串中的所有出现的子字符串
 */
public class ReplaceAllOccurrences {
    public static void main(String args[]) {
        Pattern p = Pattern.compile("Java");
        String instring = "Java and Javascript all is the best languages.";
        System.out.println("initial String: " + instring);
        Matcher m = p.matcher(instring);
        String tmp = m.replaceAll("Python");
        System.out.println("String after replacing 1st Match: " + tmp);
    }
}
