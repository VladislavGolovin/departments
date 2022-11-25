package com.task.departments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DivisionNotFoundByIdAdvice {
    @ResponseBody
    @ExceptionHandler(DivisionNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String divisionNotFoundByIdHandler(DivisionNotFoundByIdException ex) {
        return ex.getMessage();
    }
}
