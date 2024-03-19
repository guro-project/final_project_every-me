package com.everyme.domain.user.controller;

import com.everyme.domain.diet.entity.Diet;
import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.service.UserService;
import com.everyme.global.security.auth.model.dto.TokenDTO;
import com.everyme.global.security.common.utils.TokenUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
        String profileUri = String.valueOf(userService.loadProfileImg(userId));

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

    @GetMapping("/getProfileImg")
    public ResponseEntity<String> getProfileImg(@RequestParam String userId) {
        try {
            // 이미지 파일 경로 설정
            String imagePath = "build/resources/images/" + userId + "_userProfileImg.jpg"; // 이미지 파일 경로를 지정해야 합니다.

            File file = new File(imagePath);

            if (!file.exists()) {
                // 파일이 존재하지 않을 경우, 대체할 이미지를 반환하거나 에러 메시지를 반환할 수 있습니다.
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }

            byte[] fileContent = Files.readAllBytes(file.toPath());
            String base64Image = Base64.getEncoder().encodeToString(fileContent);

            // Base64로 인코딩된 이미지를 클라이언트로 응답
            return ResponseEntity.ok().body(base64Image);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file");
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
    public ResponseEntity<String> editProfileImg(@RequestPart("profileUri") MultipartFile profileUri,
                                 @RequestParam String userId) {
        if (profileUri.isEmpty()) {
            return ResponseEntity.ok("Please select a file");
        }

        try {
            byte[] bytes = profileUri.getBytes();
            Path path = Paths.get("build/resources/images/" + userId + profileUri.getOriginalFilename());
            Files.write(path, bytes);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("Failed to upload file");
        }
    }

    @PostMapping("/editProfileImg2")
    public ResponseEntity editProfileImg2(@RequestPart("imageFile") MultipartFile imageFile,
                                          @RequestPart("userId") String userId) {
        try {
            String uploadDir = "build/resources/images/";
            String fileName = imageFile.getOriginalFilename();
            String filePath = uploadDir + fileName;

            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(imageFile.getBytes());
            fos.close();

            return new ResponseEntity<>("이미지 업로드 완료", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();

            return new ResponseEntity<>("이미지 업로드에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

        }

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
