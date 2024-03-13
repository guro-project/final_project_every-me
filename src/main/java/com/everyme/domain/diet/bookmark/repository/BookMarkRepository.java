package com.everyme.domain.diet.bookmark.repository;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<DietBookMark,Integer> {
    DietBookMark findByDietNo(Integer dietNo);
}
