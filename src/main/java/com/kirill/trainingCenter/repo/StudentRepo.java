package com.kirill.trainingCenter.repo;

import com.kirill.trainingCenter.domain.Student;

import java.util.List;

public interface StudentRepo {
    void add(String name, String lastname);

    List<Student> get();

    Student get(Long id);
}
