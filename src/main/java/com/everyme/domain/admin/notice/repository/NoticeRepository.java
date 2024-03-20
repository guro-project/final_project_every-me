package com.everyme.domain.admin.notice.repository;

import com.everyme.domain.admin.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
