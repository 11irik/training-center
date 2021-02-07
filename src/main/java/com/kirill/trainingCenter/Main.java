package com.kirill.trainingCenter;


import com.kirill.trainingCenter.cli.CommandLineInterface;
import com.kirill.trainingCenter.repo.ram.CourseRepoRam;
import com.kirill.trainingCenter.repo.ram.CurriculumRepoRam;
import com.kirill.trainingCenter.repo.ram.StudentRepoRam;

public class Main {
    public static void main(String[] args) {
        new CommandLineInterface(new StudentRepoRam(), new CourseRepoRam(), new CurriculumRepoRam()).start();
    }
}
