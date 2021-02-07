package com.kirill.trainingCenter;

import com.kirill.trainingCenter.domain.Course;
import com.kirill.trainingCenter.domain.Curriculum;
import com.kirill.trainingCenter.domain.Student;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class StudentTest {

    Course courseLessWorkingHours = new Course("Git", 4);
    Course courseMoreWorkingHours = new Course("Spring", 10);
    Course courseLongDuration = new Course("Data Science", 120);

    Curriculum curriculumBeforeDay = new Curriculum("Python", LocalDateTime.of(2021, 2, 1, 8, 0));
    Curriculum curriculumMidDayStart = new Curriculum("Java", LocalDateTime.of(2021, 2, 1, 15, 0));
    Curriculum curriculumAfterDay = new Curriculum("Scala", LocalDateTime.of(2021, 2, 1, 20, 0));
    
    Curriculum curriculumMonthEdge = new Curriculum("GoLang", LocalDateTime.of(2020, 11, 30, 15, 0));
    Curriculum curriculumYearEdge = new Curriculum("C++", LocalDateTime.of(2020, 12, 31, 15, 0));
    Curriculum curriculumWeekend = new Curriculum("C++", LocalDateTime.of(2021, 2, 5, 15, 0));

    Student student = new Student("Kirill", "Lukashin");

    @Test
    public void courseStartIsBeforeWorkingHours() {
        curriculumBeforeDay.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumBeforeDay);
        LocalDateTime endDate = curriculumBeforeDay.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 1);
        Assert.assertEquals(hours, 14);
    }

    @Test
    public void courseStartIsBetweenWorkingHours() {
        curriculumMidDayStart.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumMidDayStart);
        LocalDateTime endDate = curriculumMidDayStart.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 2);
        Assert.assertEquals(hours, 11);
    }

    @Test
    public void courseStartIsAfterWorkingHours() {
        curriculumAfterDay.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumAfterDay);
        LocalDateTime endDate = curriculumAfterDay.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 2);
        Assert.assertEquals(hours, 14);
    }

    @Test
    public void courseStartIsBeforeWorkingHoursLongerDuration() {
        curriculumBeforeDay.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumBeforeDay);
        LocalDateTime endDate = curriculumBeforeDay.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 2);
        Assert.assertEquals(hours, 12);
    }

    @Test
    public void courseStartIsBetweenWorkingHoursLongerDuration() {
        curriculumMidDayStart.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumMidDayStart);
        LocalDateTime endDate = curriculumMidDayStart.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 2);
        Assert.assertEquals(hours, 17);
    }

    @Test
    public void courseStartIsAfterWorkingHoursLongerDuration() {
        curriculumAfterDay.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumAfterDay);
        LocalDateTime endDate = curriculumAfterDay.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 3);
        Assert.assertEquals(hours, 12);
    }

    @Test
    public void monthEdgeTest() {
        curriculumMonthEdge.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumMonthEdge);
        LocalDateTime endDate = curriculumMonthEdge.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 12);
        Assert.assertEquals(day, 1);
        Assert.assertEquals(hours, 17);
    }

    @Test
    public void yearEdgeTest() {
        curriculumYearEdge.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumYearEdge);
        LocalDateTime endDate = curriculumYearEdge.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int year = endDate.getYear();
        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(year, 2021);
        Assert.assertEquals(month, 1);
        Assert.assertEquals(day, 1);
        Assert.assertEquals(hours, 17);
    }

    @Test
    public void weekendTest() {
        curriculumWeekend.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumWeekend);
        LocalDateTime endDate = curriculumWeekend.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 8);
        Assert.assertEquals(hours, 17);
    }

    @Test
    public void severalCoursesLongDuration() {
        curriculumWeekend.addCourse(courseLongDuration);
        curriculumWeekend.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumWeekend);
        LocalDateTime endDate = curriculumWeekend.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 3);
        Assert.assertEquals(day, 1);
        Assert.assertEquals(hours, 17);
    }
}
