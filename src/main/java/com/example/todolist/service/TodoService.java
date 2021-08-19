package com.example.todolist.service;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<Todo> findTodos(){
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo findTodoById(Integer id ){
        return todoRepository.findById(id).orElse(null);
    }

}
