package com.xxx.example.string;

import org.junit.Test;

import java.util.Locale;

public class StringDemo {
    /**
     * 搜索字符串最后一次出现位置
     */
    @Test
    public void searchLastString() {
        String strOrig = "Hello world ,Hello Reader,Hello Sukida";
        int lastIndex = strOrig.lastIndexOf("Hello");

        if (lastIndex == -1) {
            System.out.println("Hello not found");
        } else {
            System.out.println("Last occurrence of Hello is at index "
                    + lastIndex);
        }
    }

    /**
     * 删除指定位置的字符
     */
    @Test
    public void deletingCharacter() {
        String str = "This is a Java grogram.";
        System.out.println(removeCharAt(str, 13));
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    /**
     * 替换字符串
     */
    @Test
    public void stringReplace() {
        String str = "Welcome to yiibai.com";
        System.out.println("Result 1:"+str.replace('W', 'H'));
        System.out.println("Result 2:"+str.replaceFirst("He", "Wo"));
        System.out.println("Result 3:"+str.replaceAll(".com", ".cn"));
    }

    /**
     * 反转倒置字符串
     */
    @Test
    public void stringReverse() {
        String string = "abc123";
        String reverse = new StringBuffer(string).reverse().toString();
        System.out.println("String before reverse: " + string);
        System.out.println("String after reverse: " + reverse);
    }

    /**
     * 反转倒置字符串
     */
    @Test
    public void stringReverse2() {
        String input = "yiibai.com";
        char[] try1 = input.toCharArray();
        for (int i = try1.length - 1; i >= 0; i--)
            System.out.print(try1[i]);
    }

    /**
     * 在字符串中查找词组
     */
    @Test
    public void searchString() {
        String strOrig = "Hello, Sukida";
        int intIndex = strOrig.indexOf("Su");

        if (intIndex == -1) {
            System.out.println("Su not found");
        } else {
            System.out.println("Found Su at index : " + intIndex);
        }
    }

    /**
     * 在字符串中查找词组
     */
    @Test
    public void searchString2() {
        String text = "This is my dog, it is name PigPig";
        System.out.print("Is found the word ? " + text.contains("dog"));
    }

    /**
     * 分割字符串
     */
    @Test
    public void splitStr() {
        String str = "this-is-my-dog";
        String[] temp;
        String[] temp2;
        String delimeter = "-";
        temp = str.split(delimeter);

        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
        }

        System.out.println("--------------- line -----------------");
        str = "max.min.avg.sum";
        delimeter = "\\.";
        temp2 = str.split(delimeter);
        for (int i = 0; i < temp2.length; i++) {
            System.out.println(temp2[i]);

        }

        System.out.println("--------------- line -----------------");

        String s1 = "y i i b a i . c o m";
        String[] words = s1.split("\\s");
        for (String w : words) {
            System.out.println(w);
        }
    }

    /**
     * 将字符串全部转换成大写
     */
    @Test
    public void strToUpperCase() {
        String str = "string 123 ab1 Sukida toupperCase ";
        String strUpper = str.toUpperCase();
        System.out.println("Original String: " + str);
        System.out.println("String changed to upper case: " + strUpper);
    }

    /**
     * 使用regionMatches()方法确定两个字符串中的区域匹配
     * 参数的说明：
     *      11 - 是比较开始的源字符串中的索引号
     *      second_str - 是目标字符串
     *      12 - 是从目标字符串开始比较的索引号
     *      3 - 是要比较的字符数
     */
    @Test
    public void stringRegionMatch() {
        String first_str = "Welcome to IBM";
        String second_str = "I work with IBM";

        boolean match = first_str.regionMatches(11, second_str, 12, 3);
        System.out.println("first_str[11->14] == " + "second_str[12 -> 15]: "
                + match);
    }

    /**
     * 比较字符串创建的性能
     */
    @Test
    public void stringComparePerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            String s1 = "This is a String";
            String s2 = "This is a String";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for creation" + " of String literals : "
                + (endTime - startTime) + " milli seconds");
        long startTime1 = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            String s3 = new String("This is a String");
            String s4 = new String("This is a String");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("Time taken for creation" + " of String objects : "
                + (endTime1 - startTime1) + " milli seconds");
    }

    /**
     * 优化字符串创建
     */
    @Test
    public void strInternDemo() {
        String variables[] = new String[50000];
        for (int i = 0; i < 50000; i++) {
            variables[i] = "s" + i;
        }
        long startTime0 = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            variables[i] = "hello";
        }
        long endTime0 = System.currentTimeMillis();
        System.out.println("Creation time" + " of String literals : "
                + (endTime0 - startTime0) + " ms");
        long startTime1 = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            variables[i] = new String("hello");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("Creation time of"
                + " String objects with 'new' key word : "
                + (endTime1 - startTime1) + " ms");
        long startTime2 = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            variables[i] = new String("hello");
            variables[i] = variables[i].intern();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("Creation time of"
                + " String objects with intern(): " + (endTime2 - startTime2)
                + " ms");
    }

    @Test
    public void internTest() {
        String s = new String("1");
        s.intern();//调用此方法之前，字符串常量池中已经存在了“1”
        String s2 = "1";
        System.out.println(s == s2);//jdk6:false jdk7/8:false

        String s3 = new String("1") + new String("1");//s3变量记录的地址为：new String("11")，堆中。
        //执行完上一行代码以后，字符串常量池中，不存在“11”！！！
        s3.intern();//在字符串常量池中生成“11”。jdk6中，永久代中创建了一个新的对象“11”，也就有了新的地址。
        //jdk7/jdk8中，此时常量中并没有创建“11”，而是添加一个指向堆空间中new String("11")的地址
        String s4 = "11";//s4变量记录的地址：使用的是上一行代码执行时，在字符串常量池中生成的“11”的地址
        System.out.println(s3 == s4);//jdk6:false jdk7/8:true


        String st = new String("a") + new String("b");//new String("ab")
        String st2 = st.intern();//jdk6中，在串池中创建一个字符串“ab”
        //jdk7/8中，串池中没有创建字符串“ab”，而是创建一个引用，指向new String("ab")
        System.out.println(st2=="ab");//jdk6:true  jdk8:true
        System.out.println(st=="ab");//jdk6:false  jdk8:true
    }

    /**
     * 字符串格式化
     */
    @Test
    public void stringFormat() {
        double e = Math.E;
        System.out.format("%f%n", e);
        System.out.format(Locale.GERMANY, "%-10.4f%n%n", e);

        System.out.println("------------------------");

        String name = "Hello World";
        String s1 = String.format("name %s", name);
        String s2 = String.format("value %f", 32.33434);
        String s3 = String.format("value %32.12f", 32.33434);
        System.out.print(s1);
        System.out.print("\n");
        System.out.print(s2);
        System.out.print("\n");
        System.out.print(s3);
        System.out.print("\n");
    }

    /**
     * 连接字符串
     */
    @Test
    public void stringConcatenate() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 99999; i++) {
            String result = "This is" + "testing the" + "difference"
                    + "between" + "String" + "and" + "StringBuffer";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for string"
                + "concatenation using + operator : " + (endTime - startTime)
                + " ms");
        long startTime1 = System.currentTimeMillis();

        for (int i = 0; i < 99999; i++) {
            StringBuffer result = new StringBuffer();
            result.append("This is");
            result.append("testing the");
            result.append("difference");
            result.append("between");
            result.append("String");
            result.append("and");
            result.append("StringBuffer");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("Time taken for String concatenation"
                + "using StringBuffer : " + (endTime1 - startTime1) + " ms");
    }

    /**
     * 字符串unicode
     */
    @Test
    public void stringUnicode() {
        String test_string = "Welcome to Yiibabi.com";
        System.out.println("String under test is = " + test_string);

        System.out.println("Unicode code point at"
                + " position 15 in the string is = "
                + test_string.codePointBefore(16));
    }

}
