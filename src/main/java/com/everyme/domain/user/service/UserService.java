package com.everyme.domain.user.service;

import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.model.EveryMeRole;
import com.everyme.domain.user.repository.UserRepository;
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


    public User signUp(User user) {

//        // 1. 필수 정보 입력 검증
//
        if (user.getUserId() == null || user.getUserId().isEmpty() ||
                user.getUserPass() == null || user.getUserPass().isEmpty()) {
            throw new IllegalArgumentException("아이디와 비밀번호는 필수 입력 사항입니다");
        }
//
//        // 2. 이메일 양식 검사
//
//        String emailRegex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
//        if (!user.getUserId().matches(emailRegex)) {
//            throw new IllegalArgumentException("이메일 양식에 맞지 않습니다");
//        }

        // 3. 중복되는 유저 찾아보기

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
