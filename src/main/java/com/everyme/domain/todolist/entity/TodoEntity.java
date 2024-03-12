package com.everyme.domain.todolist.entity;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_todo")
public class TodoEntity {

    @Id
    @Column(name = "todo_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "todo_contents")
    private String contents;

    @Column(name = "regist_date")
    private Date registDate;

    @Column(name = "todo_complete")
    private boolean isCompleted;


    public TodoEntity() {
    }

    public TodoEntity(Long id, String contents, Date registDate, boolean isCompleted) {
        this.id = id;
        this.contents = contents;
        this.registDate = registDate;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", contents='" + contents + '\'' +
                ", registDate=" + registDate +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
