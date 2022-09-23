package com.xxx.example.regular;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用String类的matches()方法检查日期格式是否正确
 */
public class CheckDateFormat {
    public static void main(String[] argv) {
        boolean isDate = false;
        String date1 = "9-15-1998";
        String date2 = "1997-08-16";
        String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";

        isDate = date1.matches(datePattern);
        System.out.println("Date :" + date1 + ": matches with the this date Pattern:" + datePattern + "Ans:" + isDate);

        isDate = date2.matches(datePattern);
        System.out.println("Date :" + date2 + ": matches with the this date Pattern:" + datePattern + "Ans:" + isDate);
    }

    @Test
    public void test2() {
        List<String> dates = new ArrayList<>();
        dates.add("1990-12-21");
        dates.add("1990-12-31");
        dates.add("1990-12-32");
        dates.add("09-12-12");
        dates.add("2001-02-10");
        String regex = "^([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        Pattern pattern = Pattern.compile(regex);

        for (Object date : dates) {
            Matcher matcher = pattern.matcher((CharSequence) date);
            System.out.println(date + " : " + matcher.matches());
        }
    }

}
