package com.kirill.trainingCenter;

import com.kirill.trainingCenter.domain.Course;
import com.kirill.trainingCenter.domain.Curriculum;
import com.kirill.trainingCenter.domain.Student;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class StudentTest {

    @Test
    public void courseStartIsBeforeWorkingHours() {
        Course courseLessWorkingHours = new Course("Git", 4);
        Curriculum curriculumBeforeDay = new Curriculum("Python");
        Student student = new Student("Kirill", "Lukashin");
        
        curriculumBeforeDay.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumBeforeDay, LocalDateTime.of(2021, 2, 1, 8, 0));
        
        Assert.assertEquals(curriculumBeforeDay.getEndDate().getMonthValue(), 2);
        Assert.assertEquals(curriculumBeforeDay.getEndDate().getDayOfMonth(), 1);
        Assert.assertEquals(curriculumBeforeDay.getEndDate().getHour(), 14);
    }

    @Test
    public void courseStartIsBetweenWorkingHours() {
        Course courseLessWorkingHours = new Course("Git", 4);
        Curriculum curriculumMidDayStart = new Curriculum("Java");
        Student student = new Student("Kirill", "Lukashin");
       
        curriculumMidDayStart.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumMidDayStart, LocalDateTime.of(2021, 2, 1, 15, 0));

        Assert.assertEquals(curriculumMidDayStart.getEndDate().getMonthValue(), 2);
        Assert.assertEquals(curriculumMidDayStart.getEndDate().getDayOfMonth(), 2);
        Assert.assertEquals(curriculumMidDayStart.getEndDate().getHour(), 11);
    }

    @Test
    public void courseStartIsAfterWorkingHours() {
        Course courseLessWorkingHours = new Course("Git", 4);
        Curriculum curriculumAfterDay = new Curriculum("Scala");
        Student student = new Student("Kirill", "Lukashin");
        
        curriculumAfterDay.addCourse(courseLessWorkingHours);
        student.setCurriculum(curriculumAfterDay, LocalDateTime.of(2021, 2, 1, 20, 0));

        Assert.assertEquals(curriculumAfterDay.getEndDate().getMonthValue(), 2);
        Assert.assertEquals(curriculumAfterDay.getEndDate().getDayOfMonth(), 2);
        Assert.assertEquals(curriculumAfterDay.getEndDate().getHour(), 14);
    }

    @Test
    public void courseStartIsBeforeWorkingHoursLongerDuration() {
        Course courseMoreWorkingHours = new Course("Spring", 10);
        Curriculum curriculumBeforeDay = new Curriculum("Python");
        Student student = new Student("Kirill", "Lukashin");
       
        curriculumBeforeDay.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumBeforeDay, LocalDateTime.of(2021, 2, 1, 8, 0));

        Assert.assertEquals(curriculumBeforeDay.getEndDate().getMonthValue(), 2);
        Assert.assertEquals(curriculumBeforeDay.getEndDate().getDayOfMonth(), 2);
        Assert.assertEquals(curriculumBeforeDay.getEndDate().getHour(), 12);
    }

    @Test
    public void courseStartIsBetweenWorkingHoursLongerDuration() {
        Course courseMoreWorkingHours = new Course("Spring", 10);
        Curriculum curriculumMidDayStart = new Curriculum("Java");
        Student student = new Student("Kirill", "Lukashin");
     
        curriculumMidDayStart.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumMidDayStart, LocalDateTime.of(2021, 2, 1, 15, 0));

        Assert.assertEquals(curriculumMidDayStart.getEndDate().getMonthValue(), 2);
        Assert.assertEquals(curriculumMidDayStart.getEndDate().getDayOfMonth(), 2);
        Assert.assertEquals(curriculumMidDayStart.getEndDate().getHour(), 17);
    }

    @Test
    public void courseStartIsAfterWorkingHoursLongerDuration() {
        Course courseMoreWorkingHours = new Course("Spring", 10);
        Curriculum curriculumAfterDay = new Curriculum("Scala");
        Student student = new Student("Kirill", "Lukashin");
       
        curriculumAfterDay.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumAfterDay, LocalDateTime.of(2021, 2, 1, 20, 0));

        Assert.assertEquals(curriculumAfterDay.getEndDate().getMonthValue(), 2);
        Assert.assertEquals(curriculumAfterDay.getEndDate().getDayOfMonth(), 3);
        Assert.assertEquals(curriculumAfterDay.getEndDate().getHour(), 12);
    }

    @Test
    public void monthEdgeTest() {
        Course courseMoreWorkingHours = new Course("Spring", 10);
        Curriculum curriculumMonthEdge = new Curriculum("GoLang");
        Student student = new Student("Kirill", "Lukashin");
       
        curriculumMonthEdge.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumMonthEdge, LocalDateTime.of(2020, 11, 30, 15, 0));

        Assert.assertEquals(curriculumMonthEdge.getEndDate().getMonthValue(), 12);
        Assert.assertEquals(curriculumMonthEdge.getEndDate().getDayOfMonth(), 1);
        Assert.assertEquals(curriculumMonthEdge.getEndDate().getHour(), 17);
    }

    @Test
    public void yearEdgeTest() {
        Course courseMoreWorkingHours = new Course("Spring", 10);
        Curriculum curriculumYearEdge = new Curriculum("C++");
        Student student = new Student("Kirill", "Lukashin");
       
        curriculumYearEdge.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumYearEdge, LocalDateTime.of(2020, 12, 31, 15, 0));

        Assert.assertEquals(curriculumYearEdge.getEndDate().getYear(), 2021);
        Assert.assertEquals(curriculumYearEdge.getEndDate().getMonthValue(), 1);
        Assert.assertEquals(curriculumYearEdge.getEndDate().getDayOfMonth(), 1);
        Assert.assertEquals(curriculumYearEdge.getEndDate().getHour(), 17);
    }

    @Test
    public void weekendTest() {
        Course courseMoreWorkingHours = new Course("Spring", 10);
        Curriculum curriculumWeekend = new Curriculum("C++");
        Student student = new Student("Kirill", "Lukashin");

        curriculumWeekend.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumWeekend, LocalDateTime.of(2021, 2, 5, 15, 0));

        Assert.assertEquals(curriculumWeekend.getEndDate().getMonthValue(), 2);
        Assert.assertEquals(curriculumWeekend.getEndDate().getDayOfMonth(), 8);
        Assert.assertEquals(curriculumWeekend.getEndDate().getHour(), 17);
    }

    @Test
    public void severalCoursesLongDuration() {
        Course courseMoreWorkingHours = new Course("Spring", 10);
        Course courseLongDuration = new Course("Data Science", 120);
        Curriculum curriculumWeekend = new Curriculum("C++");
        Student student = new Student("Kirill", "Lukashin");
       
        curriculumWeekend.addCourse(courseLongDuration);
        curriculumWeekend.addCourse(courseMoreWorkingHours);
        student.setCurriculum(curriculumWeekend, LocalDateTime.of(2021, 2, 5, 15, 0));

        Assert.assertEquals(curriculumWeekend.getEndDate().getMonthValue(), 3);
        Assert.assertEquals(curriculumWeekend.getEndDate().getDayOfMonth(), 1);
        Assert.assertEquals(curriculumWeekend.getEndDate().getHour(), 17);
    }
}
