package com.mongodbcrud.mapper;

import com.mongodbcrud.dto.ToDoItemRequestDto;
import com.mongodbcrud.dto.ToDoItemResponse;
import com.mongodbcrud.model.ToDo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToDoItemMapper {
    ToDo toEntity(ToDoItemRequestDto toDoItemRequestDto);

    ToDoItemResponse toDto(ToDo toDo);
}
