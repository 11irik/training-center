package com.kirill.trainingCenter.domain;

import com.kirill.trainingCenter.helper.DateHelper;

public class Student extends BaseEntity {
    private String name;
    private String lastname;
    private Curriculum curriculum;
    private Integer workingTimeFrom;
    private Integer workingTimeTo;

    public Student(String name, String lastname) {
        super();
        this.name = name;
        this.lastname = lastname;
        this.workingTimeFrom = 10;
        this.workingTimeTo = 18;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        //curriculum.setStartDate(LocalDateTime.now());todo think about it
        curriculum.setEndDate(DateHelper.getEndDate(curriculum.getStartDate(), curriculum.getDuration(), workingTimeFrom, workingTimeTo));
        this.curriculum = curriculum;
    }

    public Integer getWorkingTimeFrom() {
        return workingTimeFrom;
    }

    public Integer getWorkingTimeTo() {
        return workingTimeTo;
    }
    
    public Integer getWorkingHours() {
        return workingTimeTo - workingTimeFrom;
    }
}
