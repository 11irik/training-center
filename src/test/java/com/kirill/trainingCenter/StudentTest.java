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

    Curriculum curriculumLaterStart = new Curriculum("Java", LocalDateTime.of(2021, 2, 7, 15, 0));
    Curriculum curriculumEarlierStart = new Curriculum("Python", LocalDateTime.of(2021, 2, 7, 8, 0));
    Curriculum curriculumAfterEnd = new Curriculum("Scala", LocalDateTime.of(2021, 2, 7, 20, 0));
    Curriculum curriculumMonthEdge = new Curriculum("GoLang", LocalDateTime.of(2021, 1, 31, 15, 0));
    Curriculum curriculumYearEdge = new Curriculum("C++", LocalDateTime.of(2020, 12, 31, 15, 0));
    
    Student student = new Student("Kirill", "Lukashin");
    
    @Test
    public void courseIsLaterWorkingHours() {
        curriculumLaterStart.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumLaterStart);
        LocalDateTime endDate = curriculumLaterStart.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();
        
        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 8);
        Assert.assertEquals(hours, 11);
    }

    @Test
    public void courseIsEarlierWorkingHours() {
        curriculumEarlierStart.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumEarlierStart);
        LocalDateTime endDate = curriculumEarlierStart.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 7);
        Assert.assertEquals(hours, 14);
    }

    @Test
    public void courseIsAfterWorkingHours() {
        curriculumAfterEnd.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumAfterEnd);
        LocalDateTime endDate = curriculumAfterEnd.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 8);
        Assert.assertEquals(hours, 14);
    }

    @Test
    public void courseDurationMoreWorkingHoursEarlierStart() {
        curriculumEarlierStart.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumEarlierStart);
        LocalDateTime endDate = curriculumEarlierStart.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 8);
        Assert.assertEquals(hours, 12);
    }

    @Test
    public void courseDurationMoreWorkingHoursMidDay() {
        curriculumLaterStart.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumLaterStart);
        LocalDateTime endDate = curriculumLaterStart.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 8);
        Assert.assertEquals(hours, 17);
    }

    @Test
    public void courseDurationMoreWorkingHoursAfterWorkingHours() {
        curriculumAfterEnd.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumAfterEnd);
        LocalDateTime endDate = curriculumAfterEnd.getEndDate(student.getStartWorkingHours(), student.getEndWorkingHours());

        int month = endDate.getMonthValue();
        int day = endDate.getDayOfMonth();
        int hours = endDate.getHour();

        Assert.assertEquals(month, 2);
        Assert.assertEquals(day, 9);
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

        Assert.assertEquals(month, 2);
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
}
