package com.xxx.example.dataStructure;

public class StringReversalUsingStack {
    private String input;
    private String output;

    public StringReversalUsingStack(String in) {
        input = in;
    }

    public String doRev() {
        int stackSize = input.length();
        Stack1 theStack = new Stack1(stackSize);

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            theStack.push(ch);
        }

        output = "";
        while (!theStack.isEmpty()) {
            char ch = theStack.pop();
            output = output + ch;
        }
        return output;
    }

    public static void main(String[] args) {
        String input = "Java String And Stack";
        String output;
        StringReversalUsingStack theReverser = new StringReversalUsingStack(input);
        output = theReverser.doRev();
        System.out.println("Reversed: " + output);
    }
}

class Stack1 {
    private int maxSize;
    private char[] stackArray;
    private int top;

    public Stack1(int max) {
        maxSize = max;
        stackArray = new char[maxSize];
        top = -1;
    }

    public void push(char j) {
        stackArray[++top] = j;
    }

    public char pop() {
        return stackArray[top--];
    }

    public char peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}
