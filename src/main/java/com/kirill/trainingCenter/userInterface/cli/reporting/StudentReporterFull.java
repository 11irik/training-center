package com.kirill.trainingCenter.userInterface.cli.reporting;

import com.kirill.trainingCenter.domain.Course;
import com.kirill.trainingCenter.domain.Curriculum;
import com.kirill.trainingCenter.domain.Student;

import java.time.Duration;
import java.time.LocalDateTime;

public class StudentReporterFull extends StudentReporter{
   
    public StudentReporterFull(LocalDateTime reportDate) {
        super(reportDate);
    }

    @Override
    public void report(Student student) {
        StringBuffer sb = new StringBuffer();
        Curriculum curriculum = student.getCurriculum();

        sb.append("STUDENT: ").append(student.getLastname()).append(" ").append(student.getName()).append(('\n'));
        sb.append("WORKING TIME: FROM ").append(student.getWorkingTimeFrom()).append(" TO ").append(student.getWorkingTimeTo()).append(('\n'));

        if (student.getCurriculum() != null) {
            sb.append("CURRICULUM: ").append(student.getCurriculum().getName()).append('\n');
            sb.append("START_DATE: ").append(formatDate(student.getCurriculum().getStartDate())).append('\n');
            sb.append("END_DATE: ").append(formatDate(student.getCurriculum().getEndDate())).append('\n');
            sb.append("PROGRAM DURATION: ").append(student.getCurriculum().getDuration()).append('\n');
            Duration duration = Duration.between(reportDate, curriculum.getEndDate());
            if (reportDate.compareTo(curriculum.getEndDate()) > 0) {
                sb.append(durationToString(duration)).append(" have passed since the end.").append('\n');
            } else {
                sb.append(durationToString(duration)).append(" are left until the end.").append('\n');
            }
            sb.append("COURSE\t\t\tDURATION (hrs)").append('\n');
            sb.append("--------------------------").append('\n');

            int count = 0;
            for (Course course : student.getCurriculum().getCourseList()) {
                sb.append(++count).append(". ").append(course.getName()).append(".\t").append(course.getDuration()).append('\n');
            }
        } else {
            sb.append("NO CURRICULUM").append('\n');
        }

        System.out.println(sb.toString());
    }
}
