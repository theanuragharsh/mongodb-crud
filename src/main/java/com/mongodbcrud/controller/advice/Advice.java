package com.mongodbcrud.controller.advice;

import com.mongodbcrud.exception.ApiErrorResponse;
import com.mongodbcrud.exception.ItemAlreadyExistsException;
import com.mongodbcrud.exception.ToDoItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;

@ControllerAdvice
public class Advice extends RuntimeException {

    @ExceptionHandler(ToDoItemNotFoundException.class)
    public ResponseEntity<HashMap<String, ApiErrorResponse>> handleToDoItemNotFoundException(ToDoItemNotFoundException toDoItemNotFoundException) {
        HashMap<String, ApiErrorResponse> errorResponseHashMap = new HashMap<>();
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().category("API_ERROR").timestamp(Instant.now()).message(toDoItemNotFoundException.getMessage()).build();
        errorResponseHashMap.put("Error", apiErrorResponse);
        return new ResponseEntity<>(errorResponseHashMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<HashMap<String, ApiErrorResponse>> handleItemAlreadyExistsException(ItemAlreadyExistsException itemAlreadyExistsException) {
        HashMap<String, ApiErrorResponse> errorResponseHashMap = new HashMap<>();
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().category("API_ERROR").timestamp(Instant.now()).message(itemAlreadyExistsException.getMessage()).build();
        errorResponseHashMap.put("Error", apiErrorResponse);
        return new ResponseEntity<>(errorResponseHashMap, HttpStatus.BAD_REQUEST);
    }
}
