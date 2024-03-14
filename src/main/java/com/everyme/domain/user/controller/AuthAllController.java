package com.everyme.domain.user.controller;

import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@PreAuthorize("hasAnyAuthority('ALL')")
public class AuthAllController {

    private final UserService userService;

    public AuthAllController(UserService userService) {
        this.userService = userService;
    }


//    @PostMapping("/setUserInfo")
//    public ResponseEntity userInfo(@RequestBody User user) {
//
//        System.out.println(user);
////        User userInfo = userService.userInfo(user);
////
////        if (Objects.isNull(userInfo)) {
////            return ResponseEntity.status(500).body("정보 입력 실패");
////        }
//
//        return ResponseEntity.ok("good");
//    }
}
