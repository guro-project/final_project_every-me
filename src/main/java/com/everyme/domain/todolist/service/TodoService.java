package com.everyme.domain.todolist.service;

import com.everyme.domain.todolist.entity.TodoEntity;
import com.everyme.domain.todolist.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public List<TodoEntity> findTodosByDate(Date date) {
        return todoRepository.findByRegistDate(date);
    }


    public TodoEntity addTodo(TodoEntity todo) {
        return todoRepository.save(todo);
    }

    public TodoEntity updateTodo(TodoEntity todo) throws EntityNotFoundException {
        if(!todoRepository.existsById(todo.getId())){
            throw new EntityNotFoundException("Todo with Id " + todo.getId() + " not found");
        }
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }
}
