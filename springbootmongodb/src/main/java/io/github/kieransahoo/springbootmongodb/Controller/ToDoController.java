package io.github.kieransahoo.springbootmongodb.Controller;

import io.github.kieransahoo.springbootmongodb.Exception.ToDoException;
import io.github.kieransahoo.springbootmongodb.Repository.ToDoRepository;
import io.github.kieransahoo.springbootmongodb.Service.TodoService;
import io.github.kieransahoo.springbootmongodb.model.ToDoDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository todoRepo;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllToDos(){

        List<ToDoDTO> todos = todoService.getAllTodos();

        return new ResponseEntity<>(todos,todos.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);


    }

    @PostMapping("/todos")
    public ResponseEntity<?> createToDo(@RequestBody ToDoDTO todo){
        try{
            todoService.createTodo(todo);
            return new ResponseEntity<>(todo,HttpStatus.OK);
        }catch (ConstraintViolationException ce){
            return new ResponseEntity<>(ce.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (ToDoException te){
            return new ResponseEntity<>(te.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleToDo(@PathVariable("id") String id){

           try{
             ToDoDTO todo =  todoService.getSingleTodo(id);
             return new ResponseEntity<>(todo,HttpStatus.OK);
           }catch (Exception e){
               return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
           }

    }


    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateToDoById(@PathVariable("id") String id,@RequestBody ToDoDTO todo){
        try{
           ToDoDTO updatedTodo =  todoService.updateTodo(id, todo);
            return new ResponseEntity<>(updatedTodo,HttpStatus.OK);
        }catch (ConstraintViolationException cve){
            return new ResponseEntity<>(cve.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (ToDoException te){
            return new ResponseEntity<>(te.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteToDoById(@PathVariable("id") String id){
        try{
           ToDoDTO deleted =  todoService.deleteTodoById(id);
            return new ResponseEntity<>(deleted,HttpStatus.OK);

        }catch (ToDoException te){
            return new ResponseEntity<>(te.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
