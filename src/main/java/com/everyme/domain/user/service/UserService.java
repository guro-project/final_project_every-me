package com.everyme.domain.user.service;

import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.model.EveryMeRole;
import com.everyme.domain.user.repository.UserRepository;
import com.everyme.global.security.auth.model.dto.TokenDTO;
import com.everyme.global.security.common.utils.TokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Optional<User> findUser(String id) {
        Optional<User> user = userRepository.findByUserId(id);

        return user;
    }

    public User login(User user) {
        User login = userRepository.save(user);

        return login;
    }


    public User signUp(User user) {

        // 1. 필수 정보 입력 검증
        if (user.getUserId() == null || user.getUserId().isEmpty() ||
                user.getUserPass() == null || user.getUserPass().isEmpty()) {
            throw new IllegalArgumentException("아이디와 비밀번호는 필수 입력 사항입니다");
        }

        // 2. 중복되는 유저 찾아보기

        Optional<User> existingUser = userRepository.findByUserId(user.getUserId());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다");
        }

        // 4. Additional validations (optional)

        // 5. JPA 사용하여 데이터베이스에 저장


        user.setUserPass(encoder.encode(user.getUserPass()));
        user.setUserState("Y");
        user.setRole(EveryMeRole.USER);

        User signUp = userRepository.save(user);

        return signUp;
    }


}
