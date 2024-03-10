package com.everyme.domain.diet.bookmark.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_diet_bookmark")
public class DietBookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_bookmark_no")
    private Integer dietBookmarkNo;

    @Column
    @Nullable
    private Integer dietNo;

    @Column
    @Nullable
    private Integer userNo;

    public DietBookMark() {
    }

    public Integer getDietBookmarkNo() {
        return dietBookmarkNo;
    }

    public void setDietBookmarkNo(Integer dietBookmarkNo) {
        this.dietBookmarkNo = dietBookmarkNo;
    }

    @Nullable
    public Integer getDietNo() {
        return dietNo;
    }

    public void setDietNo(@Nullable Integer dietNo) {
        this.dietNo = dietNo;
    }

    @Nullable
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(@Nullable Integer userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "DietBookMark{" +
                "dietBookmarkNo=" + dietBookmarkNo +
                ", dietNo=" + dietNo +
                ", userNo=" + userNo +
                '}';
    }
}
