package com.everyme.domain.admin.userManage.controller;

import com.everyme.domain.admin.notice.entity.Notice;
import com.everyme.domain.admin.userManage.service.ManageService;
import com.everyme.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManageController {

    @Autowired
    private ManageService manageService;
    // 조회
    // 상세정보
    // 수정 상태, 권한
    @GetMapping("/viewusers")
    public ResponseEntity<List<User>> getAllNotices(){
        List<User> notices = manageService.getAllUsers();
        return ResponseEntity.ok(notices);
    }

    @GetMapping("/viewusers/{userNo}")
    public ResponseEntity<User> getDetailNotice(@PathVariable Integer userNo){
        User selectUser = manageService.findByUserNo(userNo);
        if(selectUser != null){
            return ResponseEntity.ok(selectUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping("/updateuser/{userNo}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer userNo, @RequestBody User user){
        Object result = manageService.updateUser(userNo, user);

        if(!(result instanceof User)){
            return ResponseEntity.status(404).body("수정 실패");
        }

        User response = (User) result;
        System.out.println(response);

        return ResponseEntity.ok(response);
    }
}
