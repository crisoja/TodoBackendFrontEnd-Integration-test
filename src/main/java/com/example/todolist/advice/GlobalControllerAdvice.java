package com.example.todolist.advice;

import com.example.todolist.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse todoNotFoundExceptionHandling(TodoNotFoundException employeeNotFoundException) {
        return new ErrorResponse(employeeNotFoundException.getMessage());
    }
}