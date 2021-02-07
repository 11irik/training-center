package com.kirill.trainingCenter.repo;

import com.kirill.trainingCenter.domain.Course;

import java.util.List;

public interface CourseRepo {
    void add(String name, Integer duration);

    List<Course> get();

    Course get(Long id);
}
