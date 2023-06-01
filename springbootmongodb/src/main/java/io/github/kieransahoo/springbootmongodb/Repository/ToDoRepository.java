package io.github.kieransahoo.springbootmongodb.Repository;

import io.github.kieransahoo.springbootmongodb.model.ToDoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ToDoRepository extends MongoRepository<ToDoDTO,String> {

    @Query("{'todo': ?0 }")
    public Optional<ToDoDTO> findByTodo(String todo);

}
