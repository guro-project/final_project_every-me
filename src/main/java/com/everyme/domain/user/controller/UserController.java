package com.everyme.domain.user.controller;

import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody User user) {

        User signup = userService.signUp(user);

        if (Objects.isNull(signup)) {
            return ResponseEntity.status(500).body("가입 실패");
        }

        return ResponseEntity.ok(signup);
    }

}
