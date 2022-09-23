package com.xxx.example.regular;

import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用Util.regex.Pattern类的matcher.replaceAll(stringname)方法来删除空格
 */
public class RemovingWhitespaces {
    public static void main(String[] argv) {
        String str = "This is a Java program. This is another Java Program.";
        String pattern = "[\\s]";
        String replace = "";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        str = m.replaceAll(replace);
        System.out.println("After Whitespaces trim: "+str);
    }

    @Test
    public void test2() {
        String s1 = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a new string: ");
        s1 = scan.nextLine();
        System.out.println("Input String is  : "+s1);
        String s2 = s1.replaceAll("\\s+","");
        System.out.println("Output String is  : "+s2);
    }
}
