package com.mongodbcrud.service.impl;

import com.mongodbcrud.exception.ItemAlreadyExistsException;
import com.mongodbcrud.exception.ToDoItemNotFoundException;
import com.mongodbcrud.model.ToDo;
import com.mongodbcrud.model.repo.ToDoRepo;
import com.mongodbcrud.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepo toDoRepo;
    private static String notFound = "Item is not present...";

    @Override
    public ResponseEntity<List<ToDo>> findAllToDo() {
        List<ToDo> toDoList = toDoRepo.findAll();
        if (toDoList.isEmpty())
            throw new ToDoItemNotFoundException(notFound);
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }

    @Override
    public ToDo findById(String id) {
        return toDoRepo.findById(id).orElseThrow(() -> new ToDoItemNotFoundException(notFound));
    }

    @Override
    public ToDo findByToDo(String todo) {
        return toDoRepo.findById(todo).orElseThrow(() -> new ToDoItemNotFoundException(notFound));
    }

    @Override
    public ToDo createTodo(ToDo toDo) {
        if (toDoRepo.findByTodo(toDo.getTodo()).isPresent())
            throw new ItemAlreadyExistsException("Item Already Exists Duplicate entry not allowed" + toDo.getTodo());
        return toDoRepo.save(toDo);
    }

    @Override
    public List<ToDo> updateTodo(String id, ToDo updatedToDo) {
        Optional<ToDo> byId = toDoRepo.findById(id);
        if (byId.isEmpty())
            throw new ToDoItemNotFoundException(notFound);

        return byId.stream().map(toDo -> {
            toDo.setTodo(updatedToDo.getTodo());
            toDo.setDescription(updatedToDo.getDescription());
            toDo.setIsCompleted(updatedToDo.getIsCompleted());
            toDo.setUpdatedAt(Date.from(Instant.now()));
            return toDoRepo.save(toDo);
        }).toList();
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        try {
            toDoRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ToDoItemNotFoundException e) {
            throw new ToDoItemNotFoundException(notFound);
        }
    }
}
