package com.example.todolist.service;

import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo findTodoById(Integer id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo ID not found!"));
    }

    public Todo updateTodo(Integer id, Todo updateTodo) {
        return updateTodoInfo(findTodoById(id), updateTodo);
    }

    private Todo updateTodoInfo(Todo todoById, Todo updateTodo) {
        if (updateTodo.isDone() != null) {
            todoById.setDone(updateTodo.isDone());
        }
        if (updateTodo.getText() != null) {
            todoById.setText(updateTodo.getText());
        }
        return todoRepository.save(todoById);
    }

    public void deleteTodo(Integer id) {
        todoRepository.delete(findTodoById(id));
    }
}
