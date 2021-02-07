package com.kirill.trainingCenter.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Curriculum extends BaseEntity {
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;
    private static final int WEEK_WORKING_DAYS = 5;
    
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
        
        int hoursLeft = getCurriculumDuration();
        int nonWorkingDays = 0;

        LocalDateTime endDate = startDate;

        //first week
        //first day
        int firstDayHours = workingHours;
        if (isDayOff(startDate)) {
            endDate = endDate.plusDays(8 - startDate.getDayOfWeek().getValue()); //todo comment
        } else {
            if (courseStartHours > startHours && courseStartHours < endHours) {
                firstDayHours = endHours - courseStartHours;
            } else {
                if (courseStartHours > endHours) {
                    endDate = endDate.plusDays(1); //skip this day
                }
            }
        }
        hoursLeft -= firstDayHours;

        if (hoursLeft <= 0) {
            hoursLeft = getCurriculumDuration();
        } else {
            endDate = endDate.plusDays(1); //first day count
            int fullDays = hoursLeft / workingHours;
            hoursLeft %= workingHours;
            int workingWeeks = fullDays / WEEK_WORKING_DAYS;
            int workingDaysFirstWeek = fullDays % WEEK_WORKING_DAYS;
            
            if (endDate.getDayOfWeek().getValue() + workingDaysFirstWeek >= 7 || endDate.getDayOfWeek().getValue() == SATURDAY) { //todo comment
                nonWorkingDays += 2;
            }
            
            nonWorkingDays += workingWeeks * 2; //weekends count
            endDate = endDate.plusDays(nonWorkingDays + fullDays);
        }

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

    public boolean isDayOff(LocalDateTime localDateTime) {
        if (localDateTime.getDayOfWeek().getValue() == SATURDAY || localDateTime.getDayOfWeek().getValue() == SUNDAY) {
            return true;
        } else {
            return false;
        }
    }
}
