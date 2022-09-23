package com.xxx.example.regular;

import org.junit.Test;

/**
 * 使用phone.matches(phoneNumberPattern)方法将列表中的电话号码与指定模式相匹配
 */
public class MatchingPhoneNumbers {
    public static void main(String args[]) {
        isPhoneValid("13877889900");
        isPhoneValid("184-585-4009");
        isPhoneValid("13977889900");
        isPhoneValid("12345678900");
        isPhoneValid("1.999-585-4009");
        isPhoneValid("089812399312");
        isPhoneValid("1 585 4009");
        isPhoneValid("136-myphone123");
        isPhoneValid("17789722552");
    }

    public static boolean isPhoneValid(String phone) {
        boolean retval = false;
        String phoneNumberPattern = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$";
        retval = phone.matches(phoneNumberPattern);
        String msg = "No, pattern:" + phone + " regex: " + phoneNumberPattern;
        if (retval) {
            msg = "Yes, pattern:" + phone + " regex: " + phoneNumberPattern;
        }
        System.out.println(msg);
        return retval;
    }

    private static boolean isValid(String s) {
        String regex = "(\\d{4}-\\d{8}|\\d{3}-\\d{8})";
        return s.matches(regex);
    }

    @Test
    public void test2() {
        System.out.println(isValid("0898-66223344"));
        System.out.println(isValid("020-86222342"));
    }
}
