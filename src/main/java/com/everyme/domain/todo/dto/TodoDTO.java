package com.everyme.domain.todo.dto;

import com.everyme.domain.user.entity.User;

import java.sql.Date;

public class TodoDTO {

    private Integer userNo;

    private String contents;

    private Date registDate;

    public TodoDTO() {
    }

    public TodoDTO(Integer userNo, String contents, Date registDate) {
        this.userNo = userNo;
        this.contents = contents;
        this.registDate = registDate;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        return "TodoDTO{" +
                "userNo=" + userNo +
                ", contents='" + contents + '\'' +
                ", registDate=" + registDate +
                '}';
    }
}