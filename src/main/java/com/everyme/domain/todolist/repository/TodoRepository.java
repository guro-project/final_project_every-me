package com.everyme.domain.todolist.repository;

import com.everyme.domain.todolist.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,Long> {

    // 날짜로 Todo를 검색하는 메서드 정의
    @Query("SELECT t FROM TodoEntity t WHERE t.registDate = :date")
    List<TodoEntity> findByRegistDate(@Param("date") Date date);


}