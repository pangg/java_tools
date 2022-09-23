package com.xxx.example.regular;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LastIndexWord {
    public static void main(String args[]) {
        String candidateString = "This is a Java example.This is another Java example.";
        Pattern p = Pattern.compile("Java");

        Matcher matcher = p.matcher(candidateString);
        matcher.find();

        int nextIndex = matcher.end();

        System.out.print("The last index of Java is:");
        System.out.println(nextIndex);
    }

    @Test
    public void test2() {
        String s1 = "Java is the one of best languagues, I like Java.";
        Pattern p1 = Pattern.compile("Java");

        Matcher m1 = p1.matcher(s1);
        m1.find();
        int nextIndex = m1.end();

        System.out.print("The last index is:");
        System.out.println(nextIndex);
    }

}
