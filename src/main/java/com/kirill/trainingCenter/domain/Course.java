package com.kirill.trainingCenter.domain;

public class Course extends BaseEntity{
    private String name;
    private Integer duration;

    public Course(String name, Integer duration) {
        super();
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
