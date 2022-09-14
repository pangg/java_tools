package com.xxx.example.collection;

import java.util.*;

/**
 * 使用Collection类的Collections.unmodifiableList()方法使一个集合为只读
 */
public class ReadonlyCollection {
    public static void main(String[] args) {
        List<String> stuff = Arrays.asList(new String[] { "a", "b" });
        List<String> list = new ArrayList<>(stuff);
        list = Collections.unmodifiableList(list);
        try {
            list.set(0, "new value");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        Set set = new HashSet(stuff);
        set = Collections.unmodifiableSet(set);
        Map map = new HashMap();
        map = Collections.unmodifiableMap(map);
        System.out.println("Collection is read-only now.");
    }
}
