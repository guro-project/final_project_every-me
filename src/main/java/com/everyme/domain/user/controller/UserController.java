package com.everyme.domain.user.controller;

import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.service.UserService;
import com.everyme.global.security.auth.model.dto.TokenDTO;
import com.everyme.global.security.common.utils.TokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loadProfileImg")
    public ResponseEntity loadProfileImg(@RequestParam String userId) {
        String profileUri = userService.loadProfileImg(userId);

        if (profileUri == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("프로필 URI를 찾을 수 없음");
        }

        return ResponseEntity.ok(profileUri);
    }

    @GetMapping("/loadUserInfo")
    public ResponseEntity loadUserInfo(@RequestParam String userId) {
        User user = userService.loadUserInfo(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody User user) {

        User signup = userService.signUp(user);

        if (Objects.isNull(signup)) {
            return ResponseEntity.status(500).body("가입 실패");
        }
        return ResponseEntity.ok(signup);
    }

    @PostMapping("/setUserInfo")
    public ResponseEntity setUserInfo(@RequestBody User user) {
        // 요청 본문을 출력하여 확인
        System.out.println(user);

        User setInfo = userService.userInfo(user);

        if (Objects.isNull(setInfo)) {
            return ResponseEntity.status(500).body("정보 입력 실패");
        }

        return ResponseEntity.ok(setInfo);
    }

    @PostMapping("/editUserInfo")
    public ResponseEntity editUserInfo(@RequestBody User user) {
        System.out.println(user);

        User editInfo = userService.userInfo(user);

        if (Objects.isNull(editInfo)) {
            return ResponseEntity.status(500).body("정보 입력 실패");
        }

        return ResponseEntity.ok(editInfo);
    }

    @PostMapping("/editProfileImg")
    public ResponseEntity editProfileImg(@RequestBody User user) {

        User editProfileImg = userService.editProfileImg(user);

        if (Objects.isNull(editProfileImg)) {
            return ResponseEntity.status(500).body("정보 입력 실패");
        }

        return ResponseEntity.ok(editProfileImg);
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody User user) {
        System.out.println(user);

        User changePassword = userService.changePassword(user);

        if (Objects.isNull(changePassword)) {
            return ResponseEntity.status(500).body("정보 입력 실패");
        }

        return ResponseEntity.ok(changePassword);
    }

}
