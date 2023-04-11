package com.mongodbcrud.controller.advice;

import com.mongodbcrud.exception.ApiErrorResponse;
import com.mongodbcrud.exception.ItemAlreadyExistsException;
import com.mongodbcrud.exception.ToDoItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;

@ControllerAdvice
public class Advice extends RuntimeException {

    private static final String ERROR_TYPE="API_ERROR";
    private static final String ERROR_KEY="ERROR";

    @ExceptionHandler(ToDoItemNotFoundException.class)
    public ResponseEntity<HashMap<String, ApiErrorResponse>> handleToDoItemNotFoundException(ToDoItemNotFoundException toDoItemNotFoundException) {
        HashMap<String, ApiErrorResponse> errorResponseHashMap = new HashMap<>();
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().category(ERROR_TYPE).timestamp(Instant.now()).message(toDoItemNotFoundException.getMessage()).build();
        errorResponseHashMap.put(ERROR_KEY, apiErrorResponse);
        return new ResponseEntity<>(errorResponseHashMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<HashMap<String, ApiErrorResponse>> handleItemAlreadyExistsException(ItemAlreadyExistsException itemAlreadyExistsException) {
        HashMap<String, ApiErrorResponse> errorResponseHashMap = new HashMap<>();
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().category(ERROR_TYPE).timestamp(Instant.now()).message(itemAlreadyExistsException.getMessage()).build();
        errorResponseHashMap.put(ERROR_KEY, apiErrorResponse);
        return new ResponseEntity<>(errorResponseHashMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<HashMap<String, ApiErrorResponse>> handleUnhandeledExceptions(Exception ex, WebRequest webRequest) {
        HashMap<String, ApiErrorResponse> errorResponseHashMap = new HashMap<>();
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().category(ERROR_TYPE).timestamp(Instant.now()).message(ex.getMessage()).build();
        errorResponseHashMap.put(ERROR_KEY, apiErrorResponse);
        return new ResponseEntity<>(errorResponseHashMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
