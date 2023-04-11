package com.mongodbcrud.repository;

import com.mongodbcrud.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
    @Query("{'todo':?0}")
    Optional<ToDo> findByTodo(String todo);
}
