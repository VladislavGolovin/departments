package com.task.departments.exceptions;

public class DivisionCannotDeleteException extends RuntimeException {
    
    public DivisionCannotDeleteException(Long parentId, int childsCount) {
        super("Could not delete division by id: " + parentId + ", because he had " + childsCount + " child divisions: ");
      }
}
