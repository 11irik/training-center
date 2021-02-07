package com.kirill.trainingCenter.db;


import com.kirill.trainingCenter.domain.Curriculum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurriculumBase {
    private final List<Curriculum> curriculumList;

    public CurriculumBase() {
        curriculumList = new ArrayList<>();

        curriculumList.add(new Curriculum("Java", LocalDateTime.now()));
    }

    public void add(String name, LocalDateTime localDateTime) {
        curriculumList.add(new Curriculum(name, localDateTime));
    }

    public List<Curriculum> get() {
        return curriculumList;
    }

    public Curriculum get(Long id) {
        for (Curriculum curriculum : curriculumList) {
            if (curriculum.getId().equals(id)) {
                return curriculum;
            }
        }
        throw new NullPointerException();
    }
}
