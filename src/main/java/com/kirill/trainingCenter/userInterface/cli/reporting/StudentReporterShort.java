package com.kirill.trainingCenter.userInterface.cli.reporting;

import com.kirill.trainingCenter.domain.Curriculum;
import com.kirill.trainingCenter.domain.Student;

import java.time.Duration;
import java.time.LocalDateTime;

public class StudentReporterShort extends StudentReporter{

    public StudentReporterShort(LocalDateTime reportDate) {
        super(reportDate);
    }

    @Override
    public void report(Student student) {
        StringBuffer sb = new StringBuffer();
        Curriculum curriculum = student.getCurriculum();

        sb.append(student.getId()).append(" ");
        sb.append(student.getLastname()).append(" ").append(student.getName()).append(" ");
        if (student.getCurriculum() != null) {
            sb.append("(").append(student.getCurriculum().getName()).append(") - ");
            Duration duration = Duration.between(reportDate, curriculum.getEndDate());
            if (reportDate.compareTo(curriculum.getEndDate()) > 0) {
                sb.append("Training completed. ")
                        .append(durationToString(duration)).append(" have passed since the end.");
            } else {
                sb.append("Training is not finished. ")
                        .append(durationToString(duration)).append(" are left until the end.");
            }
        } else {
            sb.append("NO CURRICULUM");
        }

        System.out.println(sb.toString());
    }
}
