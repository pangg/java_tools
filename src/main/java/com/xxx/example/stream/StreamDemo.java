package com.xxx.example.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @See https://juejin.cn/post/6844903849753329678 感受lambda之美
 */
public class StreamDemo {
    /**
     * collect(Collectors.toList())
     * 将流转换为list。还有toSet()，toMap()等。及早求值。
     */
    @Test
    public void collectDemo() {
        List<Student> studentList = Stream.of(new Student("路飞", 22, 175),
                new Student("红发", 40, 180),
                new Student("白胡子", 50, 185)).collect(Collectors.toList());
        System.out.println(studentList);
    }

    /**
     * filter
     * 起过滤筛选的作用。内部就是Predicate接口。惰性求值。
     */
    @Test
    public void filterDemo() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        List<Student> list = students.stream()
                .filter(student -> student.getStature() < 180)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * map
     * 转换功能，内部就是Function接口。惰性求值
     */
    @Test
    public void mapDemo() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        List<String> names = students.stream()
                .map(Student::getName) // .map(student -> student.getName())
                .collect(Collectors.toList());
        System.out.println(names);
    }

    /**
     * flatMap
     * 将多个Stream合并为一个Stream。惰性求值
     * 调用Stream.of的静态方法将两个list转换为Stream，再通过flatMap将两个流合并为一个。
     */
    public void flatMapDemo() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        List<Student> studentList = Stream.of(students,
                Arrays.asList(new Student("艾斯", 25, 183),
                        new Student("雷厉", 48, 176)))
                .flatMap(Collection::stream) // .flatMap(students1 -> students1.stream())
                .collect(Collectors.toList());
        System.out.println(studentList);
    }

    /**
     * 求集合最大或最小值
     */
    @Test
    public void maxMinDemo() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        Optional<Student> max = students.stream()
                .max(Comparator.comparing(Student::getAge));
        Optional<Student> min = students.stream()
                .min(Comparator.comparing(Student::getAge));
        // 判断是否有值
        max.ifPresent(System.out::println);
        min.ifPresent(System.out::println);
    }

    /**
     * count
     * 统计功能，一般都是结合filter使用，因为先筛选出我们需要的再统计即可。及早求值
     */
    @Test
    public void countDemo() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        long count = students.stream().filter(s1 -> s1.getAge() < 45).count();
        System.out.println("年龄小于45岁的人数是：" + count);
    }

    /**
     * reduce 操作可以实现从一组值中生成一个值
     */
    @Test
    public void reduceDemo() {
        Integer reduce = Stream.of(1, 2, 3)
                // reduce接收了一个初始值为0的累加器，依次取出值与累加器相加，最后累加器的值就是最终的结果。
                .reduce(0, Integer::sum);  // .reduce(0, (acc, x) -> acc + x)
        System.out.println(reduce);
    }
}
