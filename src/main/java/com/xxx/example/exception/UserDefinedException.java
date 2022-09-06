package com.xxx.example.exception;

public class UserDefinedException {
    public static void main(String args[]) {
        try {
            throw new MyException("Custom message");
        } catch (MyException exp) {
            exp.printStackTrace();
            System.out.println(exp);
        }
    }
}

class MyException extends Exception {
    String s1;

    MyException(String s2) {
        s1 = s2;
    }

    @Override
    public String toString() {
        return ("Output String = " + s1);
    }
}
