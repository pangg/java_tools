package com.xxx.example.regular;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用String类的matches()方法来验证电子邮件地址
 */
public class ValidateEmailFormat {
    public static void main(String[] args) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String email1 = "admin@yiibai.com";
        Boolean b = email1.matches(EMAIL_REGEX);
        System.out.println("is e-mail: " + email1 + " :Valid = " + b);
        String email2 = "admin^domain.co.in";
        b = email2.matches(EMAIL_REGEX);
        System.out.println("is e-mail: " + email2 + " :Valid = " + b);
    }

    @Test
    public void test2() {
        List<String> emails = new ArrayList<>();
        emails.add("admin@yiibai.com");
        emails.add("yes2dos@gmail.com");
        emails.add("maxsu%google-cn.com");
        emails.add("maxsua@gmail-cn.com");
        emails.add("said#@youtube.co.in");
        emails.add("atosll@domaincom");
        emails.add("kitte#gmail.com");
        emails.add("@yiibai.com");

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        for (Object email : emails) {
            Matcher matcher = pattern.matcher((CharSequence) email);
            System.out.println(email + " : " + matcher.matches());
        }
    }

}
