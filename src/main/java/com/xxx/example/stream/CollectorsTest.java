package com.xxx.example.stream;

import org.junit.Test;

import java.util.*;
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
