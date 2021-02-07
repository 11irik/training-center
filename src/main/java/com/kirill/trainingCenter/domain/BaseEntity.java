package com.kirill.trainingCenter.domain;

public class BaseEntity {
    protected static Long counter = 0L;

    protected final Long id;

    public BaseEntity() {
        id = ++counter;
    }

    public Long getId() {
        return id;
    }
}
