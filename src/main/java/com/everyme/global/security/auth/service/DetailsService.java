package com.everyme.global.security.auth.service;

import com.everyme.domain.user.service.UserService;
import com.everyme.global.security.auth.model.DetailsUser;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailsService implements UserDetailsService {

    private final UserService userService;

    public DetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 유효성 검사 진행
        if (username == null || username.equals("")) {
            throw new AuthenticationServiceException(username + " is Empty");
        }

        return userService.findUser(username)
                .map(data -> new DetailsUser(Optional.of(data)))
                .orElseThrow(() -> new AuthenticationServiceException(username));
    }
}
