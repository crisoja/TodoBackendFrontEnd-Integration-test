package com.example.todolist.exception;

public class TodoNotFoundException extends  RuntimeException{
    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    public TodoNotFoundException(String message) {
        super(message);
    }
}
