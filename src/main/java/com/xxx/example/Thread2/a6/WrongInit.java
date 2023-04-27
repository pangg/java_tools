package com.xxx.example.Thread2.a6;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程安全:
 *      发布和初始化导致线程安全问题;
 *      students 这个成员变量是在构造函数中新建的线程中进行的初始化和赋值操作，而线程的启动需要一定的时间，
 *      但是我们的 main 函数并没有进行等待就直接获取数据，导致 getStudents 获取的结果为 null，这就是在错误的时间或地点发布或初始化造成的线程安全问题。
 */
public class WrongInit {
    private Map<Integer, String> students;
    private WrongInit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                students = new HashMap<>();
                students.put(1, "王小美");
                students.put(2, "钱二宝");
                students.put(3, "周三");
                students.put(4, "赵四");
            }
        }).start();
    }

    public Map<Integer, String> getStudents() {
        return students;
    }

    public static void main(String[] args) {
        WrongInit multiThreadError6 = new WrongInit();
        System.out.println(multiThreadError6.getStudents().get(1));
    }
}
