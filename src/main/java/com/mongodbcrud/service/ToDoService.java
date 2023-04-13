package com.mongodbcrud.service;

import com.mongodbcrud.dto.ToDoItemRequestDto;
import com.mongodbcrud.dto.ToDoItemResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoService {
    List<ToDoItemResponse> findAllToDo();

    ToDoItemResponse findById(String id);

    ToDoItemResponse findByToDo(String todoItem);

    ToDoItemResponse createTodo(ToDoItemRequestDto toDoItemRequestDto);

    List<ToDoItemResponse> updateTodo(String id, ToDoItemRequestDto toDoItemRequestDto);

    ResponseEntity<String> deleteById(String id);
}
