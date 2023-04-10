package com.mongodbcrud.controller;

import com.mongodbcrud.model.ToDo;
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
    public ResponseEntity<List<ToDo>> findAllToDo() {
        return toDoService.findAllToDo();
    }

    @GetMapping("/findById/{id}")
    public ToDo findById(@PathVariable String id) {
        return toDoService.findById(id);
    }

    @GetMapping("/findByToDo/{todo}")
    public ToDo findByToDo(@PathVariable String todo) {
        return toDoService.findByToDo(todo);
    }

    @PostMapping("createTodo")
    public ToDo createTodo(@RequestBody ToDo toDo) {
        return toDoService.createTodo(toDo);
    }

    @PutMapping("/updateTodo/{id}")
    public List<ToDo> updateTodo(@PathVariable String id, @RequestBody ToDo updatedToDo){
        return toDoService.updateTodo(id, updatedToDo);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return toDoService.deleteById(id);
    }

}
