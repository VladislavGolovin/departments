package com.task.departments.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.task.departments.entities.Division;

public interface DivisionRepository extends JpaRepository<Division, Long> {
    List<Division> findByCreationDate(LocalDateTime creationDate);
    List<Division> findByParentId(Long parentId);
}
