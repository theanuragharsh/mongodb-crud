package com.mongodbcrud.service;

import com.mongodbcrud.model.ToDo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoService {
    ResponseEntity<List<ToDo>> findAllToDo();

    ToDo findById(String id);

    ToDo findByToDo(String todo);

    ToDo createTodo(ToDo toDo);

    List<ToDo> updateTodo(String id, ToDo updatedToDo);

    ResponseEntity<String> deleteById(String id);
}
