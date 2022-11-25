package com.task.departments.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.departments.entities.Division;
import com.task.departments.exceptions.DivisionCannotDeleteException;
import com.task.departments.exceptions.DivisionNotFoundByIdException;
import com.task.departments.repositories.DivisionRepository;

@RestController
public class Controller {
    
    private final DivisionRepository repo;

    public Controller(DivisionRepository repo) {
        this.repo = repo;
    }

    /**
     * Метод позволяет получить подразделения по дате
     * @param timeDate дата, которая приходит с фронта в формате "yyyy-MM-dd HH:mm"
     * @return список подразделений
     */
    @GetMapping(value = "/divisions/{timeDate}")
    public List<Division> getDivisionsByCreationDate(@PathVariable String timeDate) {
        // По какой дате искать уточнения не было, решил взять дату создания
        // Что имелось ввиду под чтением дерева подразделений? Надо было найти по дате какое-то и показать его и его родителей/дочек или только сами подходящие подразделения?
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatedTimeDate = LocalDateTime.parse(timeDate, formatter);
        // При одинаковом времени с точностью до минуты (секунды я тут не принимаю), почему-то не возвращается ни одной записи, даже если всё остальное совпадает
        // Тут ещё нужно указать диапазон поиска. Т.е. в каком диапазоне дат искать записи (между чем и чем).
        return repo.findByCreationDate(formatedTimeDate);
    }

    /**
     * Метод позволяет записать новое подразделение в БД
     * @param newDivision пришедшие данные о новом подразделении с фронта
     * @return результат сохранения в БД
     */
    @PostMapping(value = "/divisions/add")
    public Division addDivision(@RequestBody Division newDivision) {
        return repo.save(newDivision);
    }

    /**
     * Метод позволяет изменить существующее подразделение в БД
     * @param newDivision изменённые данные о подразделении
     * @param id изменяемого подразделения
     * @return результат изменения
     */
    @PutMapping(value = "/divisions/edit/{id}")
    public Division editDivision(@RequestBody Division newDivision, @PathVariable Long id) {
        // Найти подразделение, существующую запись закрыть. Создать новую
        // При этом у старой записи ограничиваем дату действия (dtTill) и проставляем дату изменения (CorrectionDate)
        // Тут можно пойти двумя путями. Не совсем понимаю где у меня будет дата создания. Я сам заполню или придёт с фронта?
        // Если дата придёт с фронта, то просто берём оттуда, иначе проставляем сами в этом методе.
        // Я подразумеваю, что в новой записи с фронта придут правильные даты. Т.е. дата создания будет "сейчас", период действия с "сейчас"
        // Иначе сеттим своими силами
        // Наследуется ли родитель? Мы ведь редактируем. Думаю да
        // Тут тоже несколько сценариев: Кого сеттить в родители? id старой записи или parentId старой записи? Я взял id
        return repo.findById(id)
                   .map(division -> {
                        division.setDtTill(newDivision.getCreationDate());
                        division.setCorrectionDate(newDivision.getCreationDate());
                        repo.save(division);
                        newDivision.setParentId(id);
                        return repo.save(newDivision);
                   })
                   .orElseThrow(() -> new DivisionNotFoundByIdException(id));
    }

    /**
     * Метод позволяет удалить существующее подразделение
     * @param id удаляемого подразделения
     */
    @DeleteMapping(value = "/divisions/delete/{id}")
    void deleteDivision(@PathVariable Long id) {
        // Нужно найти по id запись и проверить не ялвеятся ли она чьим-то родителем
        Division division = repo.findById(id).orElseThrow(() -> new DivisionNotFoundByIdException(id));
        List<Division> childDivisions = repo.findByParentId(division.getId());
        // Если запись не родительская, т.е. нет детей, то позволяем удалить
        if (childDivisions.isEmpty()) {
            repo.deleteById(id); 
        // Иначе кидаем ошибку с id родителя и кол-вом детей
        } else {
            throw new DivisionCannotDeleteException(id, childDivisions.size());
        }
        
    }
}
