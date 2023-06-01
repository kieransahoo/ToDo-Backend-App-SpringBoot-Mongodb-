package io.github.kieransahoo.springbootmongodb.Service;

import io.github.kieransahoo.springbootmongodb.Exception.ToDoException;
import io.github.kieransahoo.springbootmongodb.model.ToDoDTO;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface TodoService {

    public void createTodo(ToDoDTO todo) throws ToDoException,ConstraintViolationException;

    public List<ToDoDTO> getAllTodos();

    public ToDoDTO getSingleTodo(String id) throws ToDoException;

    public ToDoDTO updateTodo(String id ,ToDoDTO todo) throws ToDoException;

    public ToDoDTO deleteTodoById(String id )throws ToDoException;
}
