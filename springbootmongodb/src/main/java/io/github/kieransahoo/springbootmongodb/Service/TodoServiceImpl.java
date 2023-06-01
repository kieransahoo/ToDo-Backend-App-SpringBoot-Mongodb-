package io.github.kieransahoo.springbootmongodb.Service;

import io.github.kieransahoo.springbootmongodb.Exception.ToDoException;
import io.github.kieransahoo.springbootmongodb.Repository.ToDoRepository;
import io.github.kieransahoo.springbootmongodb.model.ToDoDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private ToDoRepository todoRepo;

    @Override
    public void createTodo(ToDoDTO todo) throws ToDoException, ConstraintViolationException {
       Optional<ToDoDTO> opt =  todoRepo.findByTodo(todo.getTodo());

       if(opt.isPresent()){
           throw new ToDoException(ToDoException.TodoAlreadyExists());
       }else{
           todo.setCreateAt(LocalDate.now());
           todoRepo.save(todo);
       }
    }

    @Override
    public List<ToDoDTO> getAllTodos() {
        List<ToDoDTO> todos = todoRepo.findAll();
        if(todos.size()>0){
            return todos;
        }else{
           return new ArrayList<ToDoDTO>();
        }

    }

    @Override
    public ToDoDTO getSingleTodo(String id) throws ToDoException {
        Optional<ToDoDTO> opt = todoRepo.findById(id);
        if(opt.isEmpty()){
            throw new ToDoException(ToDoException.NotFoundException(id));
        }else{
            return opt.get();
        }

    }

    @Override
    public ToDoDTO updateTodo(String id, ToDoDTO todo) throws ToDoException {
        Optional<ToDoDTO> opt = todoRepo.findById(id);
        Optional<ToDoDTO> todoWithSameName = todoRepo.findByTodo(todo.getTodo());
        if(opt.isPresent()){

            if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)){
                throw new ToDoException(ToDoException.TodoAlreadyExists());
            }

            ToDoDTO updateToDo = opt.get();
            updateToDo.setCompleted(todo.getCompleted() !=null ? todo.getCompleted() : updateToDo.getCompleted());
            updateToDo.setTodo(todo.getTodo() !=null ? todo.getTodo() : updateToDo.getTodo());
            updateToDo.setDescription(todo.getDescription()!=null ? todo.getDescription():updateToDo.getDescription());
            updateToDo.setUpdatedAt(LocalDate.now());
            todoRepo.save(updateToDo);
            return updateToDo;
        }else{
            throw new ToDoException(ToDoException.NotFoundException(id));
        }
    }

    @Override
    public ToDoDTO deleteTodoById(String id) throws ToDoException {

    Optional<ToDoDTO> optionalTodo =  todoRepo.findById(id);
    if(optionalTodo.isEmpty()){
        throw new ToDoException(ToDoException.NotFoundException(id));
    }
        todoRepo.deleteById(id);
        return optionalTodo.get();
    }
}
