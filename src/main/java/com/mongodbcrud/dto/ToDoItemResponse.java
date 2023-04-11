package com.mongodbcrud.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ToDoItemResponse {
    @Id
    private String id;
    @NotNull(message = "todo can not be empty or null")
    private String todoItem;
    @NotNull(message = "description must be provided and not null")
    private String description;
    @NotNull(message = "completion status must be either true or false and not null")
    private Boolean isCompleted;
}
