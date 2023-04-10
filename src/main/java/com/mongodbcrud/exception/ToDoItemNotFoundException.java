package com.mongodbcrud.exception;

public class ToDoItemNotFoundException extends RuntimeException {
    public ToDoItemNotFoundException(String message) {
        super(message);
    }
}
