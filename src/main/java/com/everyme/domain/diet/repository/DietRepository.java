package com.everyme.domain.diet.repository;

import com.everyme.domain.diet.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Integer> {

    List<Diet> findByUserNoAndDietCalendarDate(@Param("userNo") Integer userNo, @Param("date") Date date);
}
