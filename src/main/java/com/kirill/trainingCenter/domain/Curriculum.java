package com.kirill.trainingCenter.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Curriculum extends BaseEntity{
    private String name;
    private LocalDateTime startDate;
    private List<Course> courseList;

    public Curriculum(String name, LocalDateTime startDate) {
        super();
        this.name = name;
        this.startDate = startDate;
        courseList = new ArrayList<>();
    }

    public Curriculum(String name) {
        super();
        this.name = name;
        this.startDate = LocalDateTime.now();
        courseList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public List<Course> getCourseList() {
        return courseList;
    }

    public LocalDateTime getEndDate(Integer startHours, Integer endHours) {
        int courseStartHours = startDate.getHour();
        int workingHours = endHours - startHours;
        int duration = getCurriculumDuration();

        int firstDayHours;
        LocalDateTime endDate = startDate;
        if (courseStartHours > startHours && courseStartHours < endHours) {
            firstDayHours = endHours - courseStartHours;
        } else {
            firstDayHours = workingHours;
            if (endHours < courseStartHours) {
                endDate = endDate.plusDays(1);
            }
        }
        duration -= firstDayHours;

        if (duration <= 0) {
            return endDate.with(LocalTime.of(startHours + getCurriculumDuration(), 0));
        } else {
            int workingDays = duration / workingHours;
            int hoursLeft = duration % workingHours;

            endDate = endDate.plusDays(1);
            return endDate.plusDays(workingDays).with(LocalTime.of(startHours + hoursLeft, 0));
        }
    }

    public int getCurriculumDuration() {
        int hours = 0;
        for (Course course : courseList) {
            hours += course.getDuration();
        }
        return hours;
    }
    
    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(Course course) {
        courseList.remove(course);
    }
}
