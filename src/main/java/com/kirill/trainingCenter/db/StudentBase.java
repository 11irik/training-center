package com.kirill.trainingCenter.db;

import com.kirill.trainingCenter.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBase {
    private final List<Student> studentList;
    
    public StudentBase() {
        studentList = new ArrayList<>();

        studentList.add(new Student("Ivan",  "Ivanov"));
    }
    
    public void add(String name, String lastname) {
        studentList.add(new Student(name, lastname));
    }
    
    public List<Student> get() {
        return studentList;
    }

    public Student get(Long id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new NullPointerException();
    }
}
