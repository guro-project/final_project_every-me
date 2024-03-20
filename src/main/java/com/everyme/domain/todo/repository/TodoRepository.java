package com.everyme.domain.todo.repository;

import com.everyme.domain.todo.entity.TodoEntity;
import com.everyme.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,Integer> {

    // 날짜로 Todo를 검색하는 메서드 정의
//    @Query("SELECT t FROM TodoEntity t WHERE t.registDate = :date")
//    List<TodoEntity> findByRegistDate(@Param("date") Date date);


//    @Query("SELECT t FROM TodoEntity t WHERE t.userNo = :userNo AND t.registDate = :date")


    List<TodoEntity> findByUserNoAndRegistDate(@Param("userNo") Integer userNo, @Param("date") Date date);


}