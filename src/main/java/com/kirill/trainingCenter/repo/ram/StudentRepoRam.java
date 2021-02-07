package com.kirill.trainingCenter.repo.ram;

import com.kirill.trainingCenter.domain.Student;
import com.kirill.trainingCenter.repo.StudentRepo;

import java.util.ArrayList;
import java.util.List;

public class StudentRepoRam implements StudentRepo {
    private final List<Student> studentList;
    
    public StudentRepoRam() {
        studentList = new ArrayList<>();

        studentList.add(new Student("Ivan",  "Ivanov"));
    }
    
    @Override
    public void add(String name, String lastname) {
        studentList.add(new Student(name, lastname));
    }
    
    @Override
    public List<Student> get() {
        return studentList;
    }

    @Override
    public Student get(Long id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new NullPointerException();
    }
}
