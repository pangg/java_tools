package com.xxx.example.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTest {
    public static void main(String[] args) {
        List<Student> students1 = new ArrayList<>(3);
        students1.add(new Student("路飞", 22, 175));
        students1.add(new Student("红发", 40, 180));
        students1.add(new Student("白胡子", 50, 185));

        OutstandingClass ostClass1 = new OutstandingClass("一班", students1);

        List<Student> students2 = new ArrayList<>(students1);
        students2.remove(1);
        OutstandingClass ostClass2 = new OutstandingClass("二班", students2);
        //将ostClass1、ostClass2转换为Stream
        Stream<OutstandingClass> classStream = Stream.of(ostClass1, ostClass2);
        OutstandingClass outstandingClass = biggestGroup(classStream);
        System.out.println("人数最多的班级是：" + outstandingClass.getName());
        System.out.println("一班平均年龄是：" + averageNumberOfStudent(students1));
    }

    /**
     * 转换成块:常用的流操作是将其分解成两个集合
     * Collectors.partitioningBy
     */
    @Test
    public void partitioningByTest() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175, SpecialityEnum.SIGN));
        students.add(new Student("红发", 40, 180, SpecialityEnum.DANCE));
        students.add(new Student("白胡子", 50, 185, SpecialityEnum.RUNNING));

        Map<Boolean, List<Student>> listMap = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getSpecialities().equals(SpecialityEnum.SIGN)));
        System.out.println(listMap);
    }

    /**
     * 数据分组
     * Collectors.groupingBy接收一个Function做转换。
     */
    @Test
    public void groupingByTest() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175, SpecialityEnum.SIGN));
        students.add(new Student("红发", 40, 180, SpecialityEnum.DANCE));
        students.add(new Student("白胡子", 50, 185, SpecialityEnum.RUNNING));

        Map<SpecialityEnum, List<Student>> listMap = students.stream().collect(Collectors.groupingBy(Student::getSpecialities));
        System.out.println(listMap);
    }

    /**
     * 字符串拼接
     * Collectors.joining()
     */
    @Test
    public void joiningTest() {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175, SpecialityEnum.SIGN));
        students.add(new Student("红发", 40, 180, SpecialityEnum.DANCE));
        students.add(new Student("白胡子", 50, 185, SpecialityEnum.RUNNING));

        String names = students.stream().map(Student::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println(names);
    }

    @Test
    public void test() {

        List<Student> students = Arrays.asList(
                new Student("Alice", 18),
                new Student("Bob", 20),
                new Student("Charlie", 18)
        );
        Set<String> names = students.stream()
                .collect(Collectors.mapping(Student::getName, Collectors.toSet()));

        Set<String> names2 = students.stream().map(Student::getName)
                .collect(Collectors.toSet());

        List<String> l1 = students.stream().map(Student::getName)
                .sorted(Comparator.comparing(String::toString)).collect(Collectors.toList());
        System.out.println(l1);


        /*List<Student> students = Arrays.asList(
                new Student("Alice", 18),
                new Student("Bob", 20),
                new Student("Charlie", 18)
        );
        Map<Integer, List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        Map<Integer, Long> map2 = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));
        Map<Integer, Double> map3 = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.averagingInt(Student::getAge)));
        Map<Integer, List<Integer>> map4 = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getAge, Collectors.toList())));
        Map<Integer, List<Student>> map5 = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.collectingAndThen(Collectors.toList(),
                        list -> list.stream().sorted(Comparator.comparing(Student::getName)).collect(Collectors.toList()))));

        System.out.println(map5);*/

        /*Map<Boolean, List<String>> map = Stream.of("a", "bb", "ccc")
                .collect(Collectors.partitioningBy(x -> x.length() > 1));
        System.out.println(map);*/

        /*Map<Integer, List<String>> map = Stream.of("a", "b", "c")
                .collect(Collectors.groupingBy(String::length));
        System.out.println(map);*/

        /*Optional<Integer> max = Stream.of(1, 2, 3)
                .collect(Collectors.maxBy(Comparator.naturalOrder()));
        System.out.println(max);*/


        /*int sum = Stream.of(1, 2, 3)
                .collect(Collectors.summingInt(x -> x));
        System.out.println(sum);*/

        /*double avg = Stream.of(1, 2, 3)
                .collect(Collectors.averagingInt(x -> x));
        System.out.println(avg);*/

        /*long count = Stream.of("a", "b", "c")
                .collect(Collectors.counting());
        System.out.println(count);*/

        /*String str = Stream.of("a", "b", "c")
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(str);*/

        /*List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = list1.stream()
                .map(i -> i + 1)
                //.map(Function.identity())
                .collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list2);*/

        /*Map<String, Integer> map = Stream.of("a", "b", "c")
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);*/

        /*Set<String> set = Stream.of("a", "b", "c")
                .collect(Collectors.toSet());
        System.out.println(set);*/

        /*List<String> list = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        System.out.println(list);*/
    }

    /**
     * 获取人数最多的班级
     * @param outstandingClasses
     * @return
     */
    private static OutstandingClass biggestGroup(Stream<OutstandingClass> outstandingClasses) {
        return outstandingClasses.max(Comparator.comparing(ostClass -> ostClass.getStudents().size())) // collect(Collectors.maxBy(Comparator.comparing(ostClass -> ostClass.getStudents().size())))
                .orElseGet(OutstandingClass::new);
    }

    /**
     * 计算平均年龄
     * @param students
     * @return
     */
    private static double averageNumberOfStudent(List<Student> students) {
        return students.stream().collect(Collectors.averagingInt(Student::getAge));
    }
}
