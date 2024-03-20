package com.everyme.domain.admin.qna.repository;

import com.everyme.domain.admin.qna.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Integer> {
}
