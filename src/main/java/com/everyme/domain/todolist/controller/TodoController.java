package com.everyme.domain.todolist.controller;


import com.everyme.domain.todolist.entity.TodoEntity;
import com.everyme.domain.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }




    // 특정 날짜에 등록된 Todo를 조회하는 API 엔드포인트
    @GetMapping
    public ResponseEntity<List<TodoEntity>> getTodosByDate(@RequestParam("date") String dateString) {
        LocalDate localDate = LocalDate.parse(dateString);
        Date date = Date.valueOf(localDate); // LocalDate를 Date로 변환

        List<TodoEntity> todos = todoService.findTodosByDate(date);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoEntity> addTodo(@RequestBody TodoEntity todo) {
        TodoEntity addedTodo = todoService.addTodo(todo);
        return new ResponseEntity<>(addedTodo, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable("id") Long id, @RequestBody TodoEntity todo){
        todo.setId(id);

        TodoEntity updateTodo = todoService.updateTodo(todo);
        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}