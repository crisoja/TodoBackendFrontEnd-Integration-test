package com.example.todolist.controller;


import com.example.todolist.model.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> findTodos(){
        return todoService.findTodos();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo){
        return todoService.addTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo findTodoById(@PathVariable Integer id){
        return todoService.findTodoById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody Todo updateTodo){
        return todoService.updateTodo(id, updateTodo);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTodo(@PathVariable Integer id){
         todoService.deleteTodo(id);
    }

}
