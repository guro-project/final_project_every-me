package com.everyme.domain.user.service;

import com.everyme.domain.user.entity.User;
import com.everyme.domain.user.model.EveryMeRole;
import com.everyme.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    @PersistenceContext
    private EntityManager entityManager;

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
        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);
        System.out.println(date);


        user.setUserPass(encoder.encode(user.getUserPass()));
        user.setUserState("Y");
        user.setFirstLogin("Y");
        user.setUserRegistDate(date);
        user.setUserUpdateDate(date);
        user.setRole(EveryMeRole.USER);

        User signUp = userRepository.save(user);

        return signUp;
    }

    public User userInfo(User user) {
        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);
        user.setRole(EveryMeRole.USER);
        user.setUserUpdateDate(date);

        StringBuilder queryBuilder = new StringBuilder("UPDATE tbl_user SET ");

        List<Object> parameters = new ArrayList<>();
        int parameterIndex = 1;

        if (user.getUserNickname() != null && !Objects.equals(user.getUserNickname(), "")) {
            queryBuilder.append("USER_NICKNAME = ?, ");
            parameters.add(user.getUserNickname());
        }
        if (user.getUserGender() != null) {
            queryBuilder.append("USER_GENDER = ?, ");
            parameters.add(user.getUserGender());
        }
        if (user.getUserBirth() != null) {
            queryBuilder.append("USER_BIRTH = ?, ");
            parameters.add(user.getUserBirth());
        }
        if (user.getUserHeight() != null) {
            queryBuilder.append("USER_HEIGHT = ?, ");
            parameters.add(user.getUserHeight());
        }
        if (user.getUserWeight() != null) {
            queryBuilder.append("USER_WEIGHT = ?, ");
            parameters.add(user.getUserWeight());
        }
        if (user.getUserWeightGoal() != null) {
            queryBuilder.append("USER_WEIGHT_GOAL = ?, ");
            parameters.add(user.getUserWeightGoal());
        }

        queryBuilder.append("FIRST_LOGIN = ?, USER_UPDATE_DATE = ? WHERE USER_ID = ?");
        parameters.add("N");
        parameters.add(user.getUserUpdateDate());
        parameters.add(user.getUserId());

        Query query = entityManager.createNativeQuery(queryBuilder.toString());

        for (Object parameter : parameters) {
            query.setParameter(parameterIndex++, parameter);
        }

        query.executeUpdate();

        return user;
    }

    public User changePassword(User user) {
        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);

        user.setUserPass(encoder.encode(user.getUserPass()));
        user.setUserUpdateDate(date);
        user.setRole(EveryMeRole.USER);

        StringBuilder queryBuilder = new StringBuilder("UPDATE tbl_user SET USER_PASS = ?, USER_UPDATE_DATE = ? WHERE USER_ID = ?");

        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        query.setParameter(1, user.getUserPass());
        query.setParameter(2, user.getUserUpdateDate());
        query.setParameter(3, user.getUserId());

        query.executeUpdate();

        return user;
    }

    public User editProfileImg(User user) {
        LocalDate dateNow = LocalDate.now();
        Date date = Date.valueOf(dateNow);

        user.setUserUpdateDate(date);
        user.setRole(EveryMeRole.USER);

        System.out.println(user.getProfileUri() );

        StringBuilder queryBuilder = new StringBuilder("UPDATE tbl_user SET USER_UPDATE_DATE = ?, PROFILE_URI = ? WHERE USER_ID = ?");

        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        query.setParameter(1, user.getUserUpdateDate());
        query.setParameter(2, user.getProfileUri());
        query.setParameter(3, user.getUserId());

        query.executeUpdate();

        return user;
    }

    public String loadProfileImg(String userId) {

        Optional<User> userOptional = userRepository.findByUserId(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 사용자의 프로필 URI를 반환 (이 예시에서는 User 객체에 profileUri가 있다고 가정)
            System.out.println(user.getProfileUri());
            return user.getProfileUri();
        } else {
            // 사용자가 존재하지 않을 경우 null 반환
            return null;
        }

    }

    public User loadUserInfo(String userId) {
        Optional<User> userById = userRepository.findByUserId(userId);

        if (userById != null) {
            User user = userById.get();
            user.setRole(EveryMeRole.USER);
            user.setUserNickname(user.getUserNickname());
            user.setUserGender(user.getUserGender());
            user.setUserBirth(user.getUserBirth());
            user.setUserHeight(user.getUserHeight());
            user.setUserWeight(user.getUserWeight());
            user.setUserWeightGoal(user.getUserWeightGoal());
            user.setProfileUri(user.getProfileUri());
            return user;
        } else {
            return null;
        }

    }
}
