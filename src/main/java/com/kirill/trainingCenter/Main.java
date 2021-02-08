package com.kirill.trainingCenter;


import com.kirill.trainingCenter.cli.CommandLineInterface;
import com.kirill.trainingCenter.domain.Course;
import com.kirill.trainingCenter.domain.Curriculum;
import com.kirill.trainingCenter.domain.Student;
import com.kirill.trainingCenter.repo.CourseRepo;
import com.kirill.trainingCenter.repo.CurriculumRepo;
import com.kirill.trainingCenter.repo.StudentRepo;
import com.kirill.trainingCenter.repo.ram.CourseRepoRam;
import com.kirill.trainingCenter.repo.ram.CurriculumRepoRam;
import com.kirill.trainingCenter.repo.ram.StudentRepoRam;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        StudentRepo studentRepo = new StudentRepoRam();
        CurriculumRepo curriculumRepo = new CurriculumRepoRam();
        CourseRepo courseRepo = new CourseRepoRam();

        //TODO: remove data
        LocalDateTime weekAgo = LocalDateTime.of(2021, 1, 20, 10, 0);
        LocalDateTime monthAgo = LocalDateTime.of(2021, 1, 10, 16, 0);
        LocalDateTime yesterday = LocalDateTime.of(2021, 2, 5, 13, 0);

        studentRepo.add("Petr", "Petrov");
        studentRepo.add("Kirill", "Lukashin");
        studentRepo.add("Sidr", "Sidorov");
        Student student1 = studentRepo.get(1L);
        Student student2 = studentRepo.get(2L);
        Student student3 = studentRepo.get(3L);

        courseRepo.add("Git", 4);
        courseRepo.add("Java", 40);
        courseRepo.add("TestNG", 10);
        Course course1 = courseRepo.get(1L);
        Course course2 = courseRepo.get(2L);
        Course course3 = courseRepo.get(3L);

        curriculumRepo.add("Java Dev");
        curriculumRepo.add("Basic");
        curriculumRepo.add("All in One");
        Curriculum curriculum1 = curriculumRepo.get(1L);
        Curriculum curriculum2 = curriculumRepo.get(2L);
        Curriculum curriculum3 = curriculumRepo.get(3L);

        curriculum1.addCourse(course2);
        curriculum1.addCourse(course3);
        
        curriculum2.addCourse(course1);
        
        curriculum3.addCourse(course1);
        curriculum3.addCourse(course2);
        curriculum3.addCourse(course3);

        student1.setCurriculum(curriculum1, monthAgo);
        student2.setCurriculum(curriculum2, weekAgo);
        student3.setCurriculum(curriculum3, yesterday);

        new CommandLineInterface(studentRepo, courseRepo, curriculumRepo).start();
    }
}
