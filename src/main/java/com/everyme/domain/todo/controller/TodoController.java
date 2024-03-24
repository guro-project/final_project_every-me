package com.everyme.domain.todo.controller;

import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.todo.dto.TodoDTO;
import com.everyme.domain.todo.entity.TodoEntity;
import com.everyme.domain.todo.service.TodoService;
import com.everyme.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
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




//     특정 날짜에 등록된 Todo를 조회하는 API 엔드포인트
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
    public ResponseEntity<List<TodoEntity>> getTodosByUserAndDate(
            @RequestParam("userNo") Integer userNo,
            @RequestParam("date") String dateString
    ) {
        try {
            Date date = Date.valueOf(dateString); // 문자열로부터 Date 객체 생성
            List<TodoEntity> todos = todoService.findTodosByUserNoAndDate(userNo, date);
            return new ResponseEntity<>(todos, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // 잘못된 형식의 날짜가 전달된 경우 예외 처리
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/todoList")
    public ResponseEntity<List<TodoEntity>> getTodosByUser(
            @RequestParam("userNo") Integer userNo
    ) {
        try {
            List<TodoEntity> todos = todoService.findTodosByUserNo(userNo);
            return new ResponseEntity<>(todos, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // 잘못된 형식의 날짜가 전달된 경우 예외 처리
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping
//    public ResponseEntity<List<TodoEntity>> getTodosByUserAndDate(HttpServletRequest request, @RequestParam("date") String dateString) {
//        String userNoString = request.getHeader("userNo");
//        if (userNoString == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        Integer userNo = Integer.parseInt(userNoString);
//
//        System.out.println("userNo " + userNo);
//
//        Date date = Date.valueOf(dateString);
//        System.out.println("date " + date);
//        List<TodoEntity> todos = todoService.findTodosByUserAndDate(userNo, date);
//
//        return ResponseEntity.ok(todos);
//    }

//    @PostMapping
//    public ResponseEntity<TodoEntity> addTodo(@RequestBody TodoEntity todo) {
//        TodoEntity addedTodo = todoService.addTodo(todo);
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


    @PutMapping("/{id}/complete")
    public ResponseEntity<String> updateTodoCompletion(@PathVariable("id") Integer id, @RequestBody boolean isCompleted) {
        return todoService.updateTodoCompletion(id, isCompleted);
    }


}