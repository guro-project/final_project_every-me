package com.everyme.domain.diet.entity;

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
    private String totalKcal;

    public Diet() {
    }

    public Diet(Integer dietNo, String dietName, String totalKcal) {
        this.dietNo = dietNo;
        this.dietName = dietName;
        this.totalKcal = totalKcal;
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

    public String getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(String totalKcal) {
        this.totalKcal = totalKcal;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "dietNo=" + dietNo +
                ", dietName='" + dietName + '\'' +
                ", totalKcal='" + totalKcal + '\'' +
                '}';
    }
}
