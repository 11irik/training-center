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
        int workingHours = endHours - startHours;
        int duration = getCurriculumDuration();

        int firstDayHours;
        if (startDate.getHour() < endHours) {
            if (startHours < startDate.getHour()) {
                firstDayHours = endHours - startDate.getHour();
            } else {
                if (duration < workingHours) {
                    firstDayHours = duration;
                    return startDate.with(LocalTime.of(startHours + duration, 0));
                } else {
                    firstDayHours = workingHours;
                }
            }
        } else {
            firstDayHours = 0;//todo
        }

        duration -= firstDayHours;
        int workingDays = duration / workingHours;
        LocalDateTime endDate = startDate.plusDays(workingDays);
        if (workingDays == 0 && duration != 0) {
            endDate = endDate.plusDays(1);
        }
        
        int hoursLeft = duration - workingDays * workingHours;
        endDate = endDate.with(LocalTime.of(startHours + hoursLeft, 0));
        
        return endDate;
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
