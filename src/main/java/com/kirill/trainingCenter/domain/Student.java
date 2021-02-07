package com.kirill.trainingCenter.domain;

public class Student extends BaseEntity {
    private String name;
    private String lastname;
    private Curriculum curriculum;
    private Integer startWorkingHours;
    private Integer endWorkingHours;

    public Student(String name, String lastname) {
        super();
        this.name = name;
        this.lastname = lastname;
        this.startWorkingHours = 10;
        this.endWorkingHours = 18;
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
        this.curriculum = curriculum;
    }

    public Integer getStartWorkingHours() {
        return startWorkingHours;
    }

    public Integer getEndWorkingHours() {
        return endWorkingHours;
    }
    
    public Integer getWorkingHours() {
        return endWorkingHours - startWorkingHours;
    }
}
