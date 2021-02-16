package com.kirill.trainingCenter.userInterface.cli.reporting;

import com.kirill.trainingCenter.domain.Student;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class StudentReporter {
    
    protected final LocalDateTime reportDate;
    
    public StudentReporter(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
    
    public void report(List<Student> students) {
        System.out.println("Generating report date - " + formatDate(reportDate));
        for (Student student : students) {
            report(student);
            System.out.println();
        }
    }

    public abstract void report(Student student);

    protected String formatDate(LocalDateTime localDateTime) {
        StringBuffer sb = new StringBuffer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, E, HH:mm");
        sb.append(localDateTime.format(formatter));

        return sb.toString();
    }

    protected String durationToString(Duration duration) {
        Long days = Math.abs(duration.toDays());
        Long hours = Math.abs(duration.toHours()) - days * 24; //todo to const
        return days + " d " + hours + " hours";
    }
}
