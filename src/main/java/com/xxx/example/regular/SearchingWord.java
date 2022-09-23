package com.xxx.example.regular;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用regex.Matcher类的matcher.start()方法来搜索字符串中的特定单词
 */
public class SearchingWord {
    public static void main(String args[]) {
        String patternString = "java";
        String candidateString = "This is a java program. This is another java program.";
        // Matcher matcher = p.matcher(candidateString);
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(candidateString);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
        }
    }

    @Test
    public void test2() {
        String s1 = "yiibai is one of best IT Yiibai website.";
        String regex = "\\byiibai\\b";
        Pattern p1 = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m1 = p1.matcher(s1);

        while (m1.find()) {
            System.out.print("Start index: " + m1.start());
            System.out.print(" End index: " + m1.end() + " ");
            System.out.println(m1.group());
        }
    }
}
