package com.xxx.designPattern.iterator;

/**
 * 迭代器模式
 * 此模式用于以顺序方式访问集合对象的元素，而不需要知道其底层表示。
 */
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();

        for (Iterator iter = nameRepository.getIterator(); iter.hasNext();) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }
    }
}
