package com.xxx.designPattern.transferObject;

import java.util.ArrayList;
import java.util.List;

public class StudentBO {
    List<StudentVo> students;

    public StudentBO() {
        students = new ArrayList<>();
        StudentVo student1 = new StudentVo("Robert", 0);
        StudentVo student2 = new StudentVo("John", 1);
        students.add(student1);
        students.add(student2);
    }

    public void deleteStudent(StudentVo student) {
        students.remove(student.getRollNo());
        System.out.println("Student: Roll No " + student.getRollNo() + ", deleted from database");
    }

    //retrive list of students from the database
    public List<StudentVo> getAllStudents() {
        return students;
    }

    public StudentVo getStudent(int rollNo) {
        return students.get(rollNo);
    }

    public void updateStudent(StudentVo student) {
        students.get(student.getRollNo()).setName(student.getName());
        System.out.println("Student: Roll No " + student.getRollNo() +", updated in the database");
    }

}
