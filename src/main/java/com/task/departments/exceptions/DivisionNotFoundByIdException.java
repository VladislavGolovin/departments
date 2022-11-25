package com.task.departments.exceptions;

public class DivisionNotFoundByIdException extends RuntimeException {
    
    public DivisionNotFoundByIdException(Long id) {
        super("Could not find division by id: " + id);
      }
}
