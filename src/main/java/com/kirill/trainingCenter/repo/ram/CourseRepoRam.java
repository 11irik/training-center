package com.kirill.trainingCenter.repo.ram;

import com.kirill.trainingCenter.domain.Course;
import com.kirill.trainingCenter.repo.CourseRepo;

import java.util.ArrayList;
import java.util.List;

public class CourseRepoRam implements CourseRepo {
    private Long counter = 0L;
    private final List<Course> courseList;

    public CourseRepoRam() {
        courseList = new ArrayList<>();
    }

    @Override
    public void add(String name, Integer duration) {
        courseList.add(new Course(++counter, name, duration));
    }

    @Override
    public List<Course> get() {
        return courseList;
    }

    @Override
    public Course get(Long id) {
        for (Course course : courseList) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        throw new NullPointerException();
    }
}
