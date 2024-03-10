package com.everyme.domain.diet.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_diet")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_no")
    private Integer dietNo;

    @Column(name = "diet_name")
    private String dietName;

    @Column(name = "total_kcal")
    @Nullable
    private Double totalKcal;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "diet_category")
    private String dietCategory;

    public Diet() {
    }

    public Diet(Integer dietNo, String dietName, @Nullable Double totalKcal, String userId, String dietCategory) {
        this.dietNo = dietNo;
        this.dietName = dietName;
        this.totalKcal = totalKcal;
        this.userId = userId;
        this.dietCategory = dietCategory;
    }

    public Integer getDietNo() {
        return dietNo;
    }

    public void setDietNo(Integer dietNo) {
        this.dietNo = dietNo;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    @Nullable
    public Double getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(@Nullable Double totalKcal) {
        this.totalKcal = totalKcal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDietCategory() {
        return dietCategory;
    }

    public void setDietCategory(String dietCategory) {
        this.dietCategory = dietCategory;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "dietNo=" + dietNo +
                ", dietName='" + dietName + '\'' +
                ", totalKcal=" + totalKcal +
                ", userId='" + userId + '\'' +
                ", dietCategory='" + dietCategory + '\'' +
                '}';
    }
}
