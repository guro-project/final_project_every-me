package com.everyme.domain.todo.dto;

import com.everyme.domain.user.entity.User;

public class TodoDTO {

    private Integer userNo;

    private String contents;

    public TodoDTO() {
    }

    public TodoDTO(Integer userNo, String contents) {
        this.userNo = userNo;
        this.contents = contents;
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

    @Override
    public String toString() {
        return "TodoDTO{" +
                "userNo=" + userNo +
                ", contents='" + contents + '\'' +
                '}';
    }
}
