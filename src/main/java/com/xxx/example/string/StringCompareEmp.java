package com.xxx.example.string;

public class StringCompareEmp {
    public static void main(String[] args) {
        // compareToTest();
        // equalsTest();
        doubleEquals();
    }

    /**
     * str.compareTo(string)，str.compareToIgnoreCase(String)和compareTo(object string)的字符串类比较两个字符串，
     * 并返回比较字符串的第一个奇数字符的ascii差异。
     */
    public static void compareToTest() {
        String str = "Hello World";
        String anotherString = "hello world";
        Object objStr = str;

        System.out.println( str.compareTo(anotherString) );  // -32
        System.out.println( str.compareToIgnoreCase(anotherString) );  // 0
        System.out.println( str.compareTo(objStr.toString()));  // 0
    }

    /**
     * equals()方法将此字符串与指定的对象进行比较。当且仅当参数不为null且是表示与该对象相同的字符序列的String对象时，结果为:True。
     */
    public static void equalsTest() {
        String s1 = "yiibai";
        String s2 = "yiibai";
        String s3 = new String("yiibai");
        String s4 = new String("Yiibai");
        System.out.println(s1.equals(s2));  // true
        System.out.println(s2.equals(s3));  // true
        System.out.println(s3.equals(s4));  // false
    }

    /**
     * 使用==比较
     */
    public static void doubleEquals() {
        String s1 = "yiibai";
        String s2 = "yiibai";
        String s3 = new String("yiibai");
        String s4 = new String("Yiibai");
        System.out.println(s1 == s2);  // true
        System.out.println(s2 == s3);  // false
        System.out.println(s3 == s4);  // false
    }
}
