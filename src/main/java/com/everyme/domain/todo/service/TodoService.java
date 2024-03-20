package com.everyme.domain.todo.service;

import com.everyme.domain.todo.dto.TodoDTO;
import com.everyme.domain.todo.entity.TodoEntity;
import com.everyme.domain.todo.repository.TodoRepository;
import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


//    public List<TodoEntity> findTodosByDate(Date date) {
//        return todoRepository.findByRegistDate(date);
//    }

    public List<TodoEntity> findTodosByUserNoAndDate(Integer userNo, Date date) {
        return todoRepository.findByUserNoAndRegistDate(userNo, date);
    }


//    public List<TodoEntity> findTodosByUserAndDate(Integer userNo, Date date) {
//        return todoRepository.findByRegistDate(userNo, date);
//    }





//    public TodoEntity addTodo(TodoEntity todo) {
//
//        return todoRepository.save(todo);
//    }


//    public TodoEntity addTodo(Integer userNo, TodoDTO todoDTO) {
//        System.out.println("계획등록 서비스");
//        TodoEntity todo = new TodoEntity();
//        todo.setUserNo(todoDTO.getUserNo());
//        System.out.println("akakakak" + todo.getUserNo());
//        todo.setContents(todoDTO.getContents());
//        todo.setRegistDate(new Date(System.currentTimeMillis()));
//        todo.setCompleted(false); // 기본값으로 설정
//
//        return todoRepository.save(todo);
//    }
    public TodoEntity addTodo(TodoDTO todoDTO) {
        System.out.println("계획등록 서비스");
        TodoEntity todo = new TodoEntity();

        todo.setUserNo(todoDTO.getUserNo());
        System.out.println("akakakak" + todo.getUserNo());
        todo.setContents(todoDTO.getContents());


        todo.setRegistDate(todoDTO.getRegistDate());

        System.out.println(todoDTO.getRegistDate());

        todo.setCompleted(false); // 기본값으로 설정

        return todoRepository.save(todo);
    }

    public TodoEntity updateTodo(TodoEntity todo) throws EntityNotFoundException {
        if(!todoRepository.existsById(todo.getId())){
            throw new EntityNotFoundException("Todo with Id " + todo.getId() + " not found");
        }
        return todoRepository.save(todo);
    }

    public void deleteTodo(Integer id){
        todoRepository.deleteById(id);
    }


}