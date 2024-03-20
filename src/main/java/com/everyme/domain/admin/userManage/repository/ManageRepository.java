package com.everyme.domain.admin.userManage.repository;

import com.everyme.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageRepository extends JpaRepository<User, Integer> {
}
