package com.everyme.domain.admin.userManage.service;

import com.everyme.domain.admin.notice.entity.Notice;
import com.everyme.domain.admin.userManage.repository.ManageRepository;
import com.everyme.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ManageService {

    @Autowired
    private ManageRepository manageRepository;

    public List<User> getAllUsers() {
        return manageRepository.findAll();
    }

    public User findByUserNo(Integer userNo) {
        User user = manageRepository.findById(userNo).orElseThrow(() -> new IllegalArgumentException("존재하지 않음"));

        return user;
    }


    public Object updateUser(Integer userNo, User user) {
        Optional<User> userOptional = manageRepository.findById(userNo);

        if(userOptional.isPresent()){
            User existingUser = userOptional.get();

            existingUser.setUserState(user.getUserState());
            existingUser.setRole(user.getRole());

            User updateUser = manageRepository.save(existingUser);

            return updateUser;
        } else {
            System.out.println("유저가 존재하지않음");
            return null;
        }
    }
}
