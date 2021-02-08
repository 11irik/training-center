package com.kirill.trainingCenter.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Curriculum extends BaseEntity {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Course> courseList;

    public Curriculum(String name) {
        this.name = name;
        courseList = new ArrayList<>();
    }

    public Curriculum(Long id, String name) {
        this.id = id;
        this.name = name;
        courseList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public int getDuration() {
        int hours = 0;
        for (Course course : courseList) {
            hours += course.getDuration();
        }
        return hours;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(Course course) {
        courseList.remove(course);
    }
}
