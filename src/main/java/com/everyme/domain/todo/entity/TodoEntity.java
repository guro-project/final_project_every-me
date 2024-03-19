package com.everyme.domain.todo.entity;

import com.everyme.domain.user.entity.User;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_todo")
public class TodoEntity {

    @Id
    @Column(name = "todo_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "user_no")
    private Integer userNo;

    @Column(name = "todo_contents")
    private String contents;

    @Column(name = "regist_date")
    private Date registDate;

    @Column(name = "todo_complete")
    private boolean isCompleted;


    public TodoEntity() {
    }

    public TodoEntity(Integer id, Integer userNo, String contents, Date registDate, boolean isCompleted) {
        this.id = id;
        this.userNo = userNo;
        this.contents = contents;
        this.registDate = registDate;
        this.isCompleted = isCompleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", userNo=" + userNo +
                ", contents='" + contents + '\'' +
                ", registDate=" + registDate +
                ", isCompleted=" + isCompleted +
                '}';
    }
}