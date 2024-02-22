package com.everyme.domain.user.service;

import com.everyme.domain.user.entity.User;
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
        // 유효성 체크 코드 필요


        user.setUserPass(encoder.encode(user.getUserPass()));
        user.setState("Y");

        User signUp = userRepository.save(user);

        return signUp;
    }

}
