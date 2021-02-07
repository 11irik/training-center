package com.kirill.trainingCenter.db;

import com.kirill.trainingCenter.domain.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBase {
    private final List<Course> courseList;

    public CourseBase() {
        courseList = new ArrayList<>();
        
        courseList.add(new Course("Hib", 12));
    }

    public void add(String name, Integer duration) {
        courseList.add(new Course(name, duration));
    }

    public List<Course> get() {
        return courseList;
    }

    public Course get(Long id) {
        for (Course course : courseList) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        throw new NullPointerException();
    }
}
