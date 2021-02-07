package com.kirill.trainingCenter.repo.ram;


import com.kirill.trainingCenter.domain.Curriculum;
import com.kirill.trainingCenter.repo.CurriculumRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurriculumRepoRam implements CurriculumRepo {
    private final List<Curriculum> curriculumList;

    public CurriculumRepoRam() {
        curriculumList = new ArrayList<>();

        curriculumList.add(new Curriculum("Java", LocalDateTime.now()));
    }

    @Override
    public void add(String name, LocalDateTime localDateTime) {
        curriculumList.add(new Curriculum(name, localDateTime));
    }

    @Override
    public List<Curriculum> get() {
        return curriculumList;
    }

    @Override
    public Curriculum get(Long id) {
        for (Curriculum curriculum : curriculumList) {
            if (curriculum.getId().equals(id)) {
                return curriculum;
            }
        }
        throw new NullPointerException();
    }
}
