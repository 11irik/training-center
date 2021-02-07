package com.kirill.trainingCenter.cli;

import com.kirill.trainingCenter.domain.Course;
import com.kirill.trainingCenter.domain.Curriculum;
import com.kirill.trainingCenter.domain.Student;
import com.kirill.trainingCenter.repo.CourseRepo;
import com.kirill.trainingCenter.repo.CurriculumRepo;
import com.kirill.trainingCenter.repo.StudentRepo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class CommandLineInterface {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final CurriculumRepo curriculumRepo;

    public CommandLineInterface(StudentRepo studentRepo, CourseRepo courseRepo, CurriculumRepo curriculumRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.curriculumRepo = curriculumRepo;
    }

    private static final String ADD_STUDENT = "S";
    private static final String[] ADD_STUDENT_ARGS = {"NAME", "LASTNAME"};
    private static final String GET_STUDENTS = "SS";
    private static final String GET_STUDENT = "SSS";
    private static final String[] GET_STUDENT_ARGS = {"ID"};

    private static final String ADD_CURRICULUM = "C";
    private static final String[] ADD_CURRICULUM_ARGS = {"NAME", "START DATE (Optional)"};
    private static final String GET_CURRICULUMS = "CS";

    private static final String ADD_COURSE = "CC";
    private static final String[] ADD_COURSE_ARGS = {"NAME", "DURATION"};
    private static final String GET_COURSES = "CCS";

    private static final String ADD_COURSE_TO_CURRICULUM = "MC";
    private static final String REMOVE_COURSE_FROM_CURRICULUM = "UC";
    private static final String[] MAP_COURSE_TO_CURRICULUM_ARGS = {"CURRICULUM ID", "COURSE ID"};

    private static final String ADD_CURRICULUM_TO_STUDENT = "MS";
    private static final String REMOVE_CURRICULUM_FROM_STUDENT = "US";
    private static final String[] MAP_CURRICULUM_TO_STUDENT_ARGS = {"STUDENT ID", "CURRICULUM ID"};

    private static final String GET_HINT = "HINT";
    private static final String EXIT = "EXIT";

    private static final String UNKNOWN_COMMAND = "UNKNOWN COMMAND";
    private static final String WRONG_ARGUMENT = "Wrong argument(s)";

    public void start() {
        System.out.println(getHint());
        String command;
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            System.out.print(">>> ");
            LinkedList<String> args = new LinkedList<>(Arrays.asList(sc.nextLine().split(" ")));
            command = args.get(0).toUpperCase(Locale.ROOT);
            args.remove(0);

            try {
                switch (command) {
                    case ADD_STUDENT:
                        if (args.size() != ADD_STUDENT_ARGS.length) {
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            studentRepo.add(args.get(0), args.get(1));
                        }
                        break;
                    case GET_STUDENTS:
                        for (Student student : studentRepo.get()) {
                            System.out.println(student);
                        }
                        break;
                    case GET_STUDENT:
                        if (args.size() != GET_STUDENT_ARGS.length) {
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            Student student = studentRepo.get(Long.parseLong(args.get(0)));
                            System.out.println(getStudentInfo(student));
                        }
                        break;
                    case ADD_CURRICULUM:
                        if (args.size() != ADD_CURRICULUM_ARGS.length - 1) {
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            curriculumRepo.add(args.get(0), LocalDateTime.now());
                        }
                        break;
                    case GET_CURRICULUMS:
                        for (Curriculum curriculum : curriculumRepo.get()) {
                            System.out.println(curriculum);
                        }
                        break;
                    case ADD_COURSE:
                        if (args.size() != ADD_COURSE_ARGS.length) {
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            courseRepo.add(args.get(0), Integer.parseInt(args.get(1)));
                        }
                        break;
                    case GET_COURSES:
                        for (Course course : courseRepo.get()) {
                            System.out.println(course);
                        }
                        break;
                    case ADD_COURSE_TO_CURRICULUM:
                        if (args.size() != MAP_COURSE_TO_CURRICULUM_ARGS.length) {
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            Long curriculumId = Long.parseLong(args.get(0));
                            Long courseId = Long.parseLong(args.get(1));

                            Curriculum curriculum = curriculumRepo.get(curriculumId);
                            Course course = courseRepo.get(courseId);
                            curriculum.addCourse(course);
                        }
                        break;
                    case REMOVE_COURSE_FROM_CURRICULUM:
                        if (args.size() != MAP_COURSE_TO_CURRICULUM_ARGS.length) {
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            Long curriculumId = Long.parseLong(args.get(0));
                            Long courseId = Long.parseLong(args.get(0));

                            Curriculum curriculum = curriculumRepo.get(curriculumId);
                            Course course = courseRepo.get(courseId);
                            curriculum.removeCourse(course);
                        }
                        break;
                    case ADD_CURRICULUM_TO_STUDENT:
                        if (args.size() != MAP_CURRICULUM_TO_STUDENT_ARGS.length) {
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            Long studentId = Long.parseLong(args.get(0));
                            Long curriculumId = Long.parseLong(args.get(1));

                            Curriculum curriculum = curriculumRepo.get(curriculumId);
                            Student student = studentRepo.get(studentId);
                            student.setCurriculum(curriculum);
                        }
                        break;
                    case REMOVE_CURRICULUM_FROM_STUDENT:
                        if (args.size() != 1) { //TODO FIX
                            System.out.println(WRONG_ARGUMENT);
                        } else {
                            Long studentId = Long.parseLong(args.get(0));
                            Student student = studentRepo.get(studentId);
                            student.setCurriculum(null);
                        }
                        break;
                    case GET_HINT:
                        System.out.println(getHint());
                        break;
                    case EXIT:
                        return;
                    default:
                        System.out.println(UNKNOWN_COMMAND);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private static String getHint() {
        StringBuffer sb = new StringBuffer();
        sb.append(ADD_STUDENT).append(" ").append(Arrays.toString(ADD_STUDENT_ARGS)).append('\n');
        sb.append(GET_STUDENTS).append('\n');
        sb.append(GET_STUDENT).append(" ").append(Arrays.toString(GET_STUDENT_ARGS)).append('\n');
        sb.append(ADD_CURRICULUM).append(" ").append(Arrays.toString(ADD_CURRICULUM_ARGS)).append('\n');
        sb.append(GET_CURRICULUMS).append('\n');
        sb.append(ADD_COURSE).append(" ").append(Arrays.toString(ADD_COURSE_ARGS)).append('\n');
        sb.append(GET_COURSES).append('\n');

        sb.append(ADD_COURSE_TO_CURRICULUM).append(" ").append(Arrays.toString(MAP_COURSE_TO_CURRICULUM_ARGS)).append('\n');
        sb.append(REMOVE_COURSE_FROM_CURRICULUM).append(" ").append(Arrays.toString(MAP_COURSE_TO_CURRICULUM_ARGS)).append('\n');

        sb.append(ADD_CURRICULUM_TO_STUDENT).append(" ").append(Arrays.toString(MAP_CURRICULUM_TO_STUDENT_ARGS)).append('\n');
        sb.append(REMOVE_CURRICULUM_FROM_STUDENT).append(" ").append(Arrays.toString(MAP_CURRICULUM_TO_STUDENT_ARGS)).append('\n');
        
        sb.append(GET_HINT).append('\n');
        sb.append(EXIT).append('\n');

        return sb.toString();
    }
    
    private static String getStudentInfo(Student student) {
        StringBuffer sb = new StringBuffer();
        
        sb.append("STUDENT: ").append(student.getLastname()).append(" ").append(student.getName()).append(('\n'));
        sb.append("WORKING TIME: FROM ").append(student.getWorkingTimeFrom()).append(" TO ").append(student.getWorkingTimeTo()).append(('\n'));


        if (student.getCurriculum() != null) {
            sb.append("CURRICULUM: ").append(student.getCurriculum().getName()).append('\n');
            sb.append("START_DATE: ").append(formatDate(student.getCurriculum().getStartDate()));
            sb.append("PROGRAM DURATION: : ").append(student.getCurriculum().getDuration());
            sb.append("COURSE\t\t\tDURATION (hrs)");
            sb.append("--------------------------");

            int count = 0;
            for (Course course : student.getCurriculum().getCourseList()) {
                sb.append(++count).append(". ").append(course.getName()).append(".\t").append(course.getDuration());
            }
        } else {
            sb.append("NO CURRICULUM").append('\n');
        }
        
        return sb.toString();
    }
    
    private static String formatDate(LocalDateTime localDateTime) {
        StringBuffer sb = new StringBuffer();
        sb.append(localDateTime.getDayOfMonth()).append(" ")
                .append(localDateTime.getMonth()).append(" ")
                .append(localDateTime.getYear()).append(" - ")
                .append(localDateTime.getDayOfWeek());
        
        return sb.toString();
    }
}
