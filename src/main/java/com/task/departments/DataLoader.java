package com.task.departments;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.task.departments.entities.Division;
import com.task.departments.repositories.DivisionRepository;

/**
 * Данный класс позволяет наполнить нашу таблицу divisions хоть-какими-то первоначальными данными
 */
@Component
public class DataLoader implements ApplicationRunner {
    private final DivisionRepository repo;

    public DataLoader(DivisionRepository repo) {
        this.repo = repo;
    }

    public void run(ApplicationArguments args) {
        List<Division> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            Division division = new Division();
            division.setName("division" + i);
            division.setCreationDate(LocalDateTime.now());
            division.setDtFrom(LocalDateTime.now());
            division.setDtTill(LocalDateTime.now());

            list.add(division);
        }

        repo.saveAll(list);
    }
}
