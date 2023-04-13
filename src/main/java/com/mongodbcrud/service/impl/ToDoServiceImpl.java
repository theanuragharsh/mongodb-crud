package com.mongodbcrud.service.impl;

import com.mongodbcrud.dto.ToDoItemRequestDto;
import com.mongodbcrud.dto.ToDoItemResponse;
import com.mongodbcrud.exception.ItemAlreadyExistsException;
import com.mongodbcrud.exception.ToDoItemNotFoundException;
import com.mongodbcrud.mapper.ToDoItemMapper;
import com.mongodbcrud.model.ToDo;
import com.mongodbcrud.repository.ToDoRepository;
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
    private final ToDoRepository toDoRepository;
    private final ToDoItemMapper toDoItemMapper;
    private static String notFound = "Item is not present...";

    @Override
    public List<ToDoItemResponse> findAllToDo() {
        List<ToDoItemResponse> toDoList = toDoRepository.findAll().stream().map(toDoItemMapper::toDto).toList();
        if (toDoList.isEmpty())
            throw new ToDoItemNotFoundException(notFound);
        return toDoList;
    }

    @Override
    public ToDoItemResponse findById(String id) {
        return toDoRepository.findById(id)
                .map(toDoItemMapper::toDto)
                .orElseThrow(() -> new ToDoItemNotFoundException(notFound));
    }

    @Override
    public ToDoItemResponse findByToDo(String todoItem) {
        return toDoRepository.findById(todoItem)
                .map(toDoItemMapper::toDto)
                .orElseThrow(() -> new ToDoItemNotFoundException(notFound));
    }

    @Override
    public ToDoItemResponse createTodo(ToDoItemRequestDto toDoItemRequestDto) {
        if (toDoRepository.findByTodo(toDoItemRequestDto.getTodoItem()).isPresent())
            throw new ItemAlreadyExistsException("Item Already Exists Duplicate entry not allowed" + toDoItemRequestDto.getTodoItem());
        return toDoItemMapper.toDto(toDoRepository.save(toDoItemMapper.toEntity(toDoItemRequestDto)));
    }

    @Override
    public List<ToDoItemResponse> updateTodo(String id, ToDoItemRequestDto toDoItemRequestDto) {
        Optional<ToDo> byId = toDoRepository.findById(id);
        if (byId.isEmpty())
            throw new ToDoItemNotFoundException(notFound);

        return byId.stream().map(toDo -> {
            toDo.setTodoItem(toDoItemRequestDto.getTodoItem());
            toDo.setDescription(toDoItemRequestDto.getDescription());
            toDo.setIsCompleted(toDoItemRequestDto.getIsCompleted());
            toDo.setUpdatedAt(Date.from(Instant.now()));
            return toDoRepository.save(toDo);
        }).map(toDoItemMapper::toDto).toList();
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        try {
            toDoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ToDoItemNotFoundException e) {
            throw new ToDoItemNotFoundException(notFound);
        }
    }
}
