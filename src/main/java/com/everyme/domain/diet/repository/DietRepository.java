package com.everyme.domain.diet.repository;

import com.everyme.domain.diet.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Integer> {
}
