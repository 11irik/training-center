package com.kirill.trainingCenter.domain;

public class Course extends BaseEntity{
    private String name;
    private Integer duration;

    public Course(String name, Integer duration) {
        this.name = name;
        this.duration = duration;
    }

    public Course(Long id, String name, Integer duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }
    
    public Integer getDuration() {
        return duration;
    }
}
