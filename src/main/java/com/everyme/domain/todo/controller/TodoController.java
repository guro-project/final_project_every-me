package com.everyme.domain.todo.controller;

import com.everyme.domain.todo.dto.TodoDTO;
import com.everyme.domain.todo.entity.TodoEntity;
import com.everyme.domain.todo.service.TodoService;
import com.everyme.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }




    // 특정 날짜에 등록된 Todo를 조회하는 API 엔드포인트
//    @GetMapping
//    public ResponseEntity<List<TodoEntity>> getTodosByDate(@RequestParam("date") String dateString) {
//        System.out.println("today : " + dateString);
//
////        LocalDate localDate = LocalDate.parse(dateString);
//        Date date = Date.valueOf(dateString); // LocalDate를 Date로 변환
//        System.out.println("changed today : " + date);
//
//        List<TodoEntity> todos = todoService.findTodosByDate(date);
//        return new ResponseEntity<>(todos, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<TodoEntity>> getTodosByUserAndDate(@RequestParam("userNo") Integer userNo, @RequestParam("date") String dateString) {
        Date date = Date.valueOf(dateString);
        List<TodoEntity> todos = todoService.findTodosByUserAndDate(userNo, date);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<TodoEntity> addTodo(@RequestBody TodoEntity todo) {
//        TodoEntity addedTodo = todoService.addTodo(todo);
//        return new ResponseEntity<>(addedTodo, HttpStatus.CREATED);
//    }

//    @PostMapping
//    public ResponseEntity<TodoEntity> addTodoForUser(@RequestParam("userNo") Integer userNo, @RequestBody TodoDTO todoDTO) {
//        System.out.println("계획 추가 컨트롤러");
//        TodoEntity addedTodo = todoService.addTodo(userNo, todoDTO);
//        return new ResponseEntity<>(addedTodo, HttpStatus.CREATED);
//    }
        @PostMapping
        public ResponseEntity<TodoEntity> addTodoForUser(@RequestBody TodoDTO todoDTO) {
            System.out.println("계획 추가 컨트롤러");
            TodoEntity addedTodo = todoService.addTodo(todoDTO);
            return ResponseEntity.ok(addedTodo);
        }


    @PutMapping("/{id}")
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable("id") Integer id, @RequestBody TodoEntity todo){
        todo.setId(id);

        TodoEntity updateTodo = todoService.updateTodo(todo);
        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Integer id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}