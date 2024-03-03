package com.everyme.domain.user.entity;

import com.everyme.domain.user.model.EveryMeRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "TBL_USER")
public class User {

    @Id
    @Column(name = "USER_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNo;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_PASS")
    private String userPass;

    @Column(name = "USER_SEC_PASS")
    private String userScdPass;

    @Column(name = "USER_NICKNAME")
    private String userNickname;

    @Column(name = "USER_BIRTH")
    private LocalDate userBirth;

    @Column(name = "USER_GENDER")
    private String userGender;

    @Column(name = "USER_HEIGHT")
    private Integer userHeight;

    @Column(name = "USER_WEIGHT")
    private Integer userWeight;

    @Column(name = "USER_WEIGHT_GOAL")
    private Integer userWeightGoal;

    @Column(name = "USER_STATE", nullable = false)
    private String userState;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private EveryMeRole role;

    @Column(name = "USER_REGIST_DATE")
    private LocalDate userRegistDate;

    @Column(name = "USER_UPDATE_DATE")
    private LocalDate userUpdateDate;

    public List<String> getRoleList() {
        if (this.role.getRole().length() > 0) {
            return Arrays.asList(this.role.getRole().split(","));
        }

        return new ArrayList<>();
    }

    public User() {
    }

    public User(Integer userNo, String provider, String userId, String userPass, String userScdPass, String userNickname, LocalDate userBirth, String userGender, Integer userHeight, Integer userWeight, Integer userWeightGoal, String userState, EveryMeRole role, LocalDate userRegistDate, LocalDate userUpdateDate) {
        this.userNo = userNo;
        this.provider = provider;
        this.userId = userId;
        this.userPass = userPass;
        this.userScdPass = userScdPass;
        this.userNickname = userNickname;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userWeightGoal = userWeightGoal;
        this.userState = userState;
        this.role = role;
        this.userRegistDate = userRegistDate;
        this.userUpdateDate = userUpdateDate;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserScdPass() {
        return userScdPass;
    }

    public void setUserScdPass(String userScdPass) {
        this.userScdPass = userScdPass;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public LocalDate getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(LocalDate userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Integer getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(Integer userHeight) {
        this.userHeight = userHeight;
    }

    public Integer getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(Integer userWeight) {
        this.userWeight = userWeight;
    }

    public Integer getUserWeightGoal() {
        return userWeightGoal;
    }

    public void setUserWeightGoal(Integer userWeightGoal) {
        this.userWeightGoal = userWeightGoal;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public EveryMeRole getRole() {
        return role;
    }

    public void setRole(EveryMeRole role) {
        this.role = role;
    }

    public LocalDate getUserRegistDate() {
        return userRegistDate;
    }

    public void setUserRegistDate(LocalDate userRegistDate) {
        this.userRegistDate = userRegistDate;
    }

    public LocalDate getUserUpdateDate() {
        return userUpdateDate;
    }

    public void setUserUpdateDate(LocalDate userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", provider='" + provider + '\'' +
                ", userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userScdPass='" + userScdPass + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userBirth=" + userBirth +
                ", userGender='" + userGender + '\'' +
                ", userHeight=" + userHeight +
                ", userWeight=" + userWeight +
                ", userWeightGoal=" + userWeightGoal +
                ", userState='" + userState + '\'' +
                ", role=" + role +
                ", userRegistDate=" + userRegistDate +
                ", userUpdateDate=" + userUpdateDate +
                '}';
    }
}
