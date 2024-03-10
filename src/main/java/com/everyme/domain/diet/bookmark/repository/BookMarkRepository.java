package com.everyme.domain.diet.bookmark.repository;

import com.everyme.domain.diet.bookmark.entity.DietBookMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<DietBookMark,Integer> {
}
