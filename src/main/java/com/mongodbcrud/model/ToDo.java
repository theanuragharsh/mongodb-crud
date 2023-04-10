package com.mongodbcrud.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "ToDo")
public class ToDo {
    @Id
    private String id;
    @NotNull(message = "todo can not be empty or null")
    private String todo;
    @NotNull(message = "description must be provided and not null")
    private String description;
    @NotNull(message = "completion status must be either true or false and not null")
    private Boolean isCompleted;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

}
