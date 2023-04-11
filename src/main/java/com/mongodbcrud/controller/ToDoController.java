package com.mongodbcrud.controller;

import com.mongodbcrud.dto.ToDoItemRequestDto;
import com.mongodbcrud.dto.ToDoItemResponse;
import com.mongodbcrud.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ToDoItemResponse>> findAllToDo() {
        return toDoService.findAllToDo();
    }

    @GetMapping("/findById/{id}")
    public ToDoItemResponse findById(@PathVariable String id) {
        return toDoService.findById(id);
    }

    @GetMapping("/findByToDo/{todo}")
    public ToDoItemResponse findByToDo(@PathVariable String todoItem) {
        return toDoService.findByToDo(todoItem);
    }

    @PostMapping("createTodo")
    public ToDoItemResponse createTodo(@RequestBody ToDoItemRequestDto toDoItemRequestDto) {
        return toDoService.createTodo(toDoItemRequestDto);
    }

    @PutMapping("/updateTodo/{id}")
    public List<ToDoItemResponse> updateTodo(@PathVariable String id, @RequestBody ToDoItemRequestDto toDoItemRequestDto) {
        return toDoService.updateTodo(id, toDoItemRequestDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return toDoService.deleteById(id);
    }

}
