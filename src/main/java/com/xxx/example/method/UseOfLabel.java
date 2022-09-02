package com.xxx.example.method;

public class UseOfLabel {
    public static void main(String[] args) {
        String strSearch = "This is the string in which you have to search for a substring.";
        String substring = "substring";
        boolean found = false;
        int max = strSearch.length() - substring.length();
        testlbl: for (int i = 0; i <= max; i++) {
            int length = substring.length();
            int j = i;
            int k = 0;
            while (length-- != 0) {
                System.out.println(System.out.printf("i=%d, %s, %s", i, strSearch.charAt(j), substring.charAt(k)));
                if (strSearch.charAt(j++) != substring.charAt(k++)) {
                    continue testlbl;
                }
            }
            found = true;
            break testlbl;
        }
        if (found) {
            System.out.println("Found the substring .");
        } else {
            System.out.println("did not find the substing in the string.");
        }
    }
}
