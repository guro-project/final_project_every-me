package com.everyme.domain.diet.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "tbl_diet")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_no")
    private Integer dietNo;

    @Column(name = "diet_name")
    @Nullable
    private String dietName;

    @Column(name = "user_no")
    @Nullable
    private Integer userNo;

    @Column(name = "total_kcal")
    @Nullable
    private Double totalKcal;

    @Column(name = "total_carbohydrate")
    @Nullable
    private Double totalCarbohydrate;

    @Column(name = "total_protein")
    @Nullable
    private Double totalProtein;

    @Column(name = "total_province")
    @Nullable
    private Double totalProvince;

    @Column(name = "total_salt")
    @Nullable
    private Double totalSalt;

    @Column(name = "ingredient_name")
    @Nullable
    private String ingredientName;

    @Column(name = "diet_status")
    @Nullable
    private String dietStatus;

    @Column(name = "diet_regist_date")
    @Nullable
    private Date dietRegistDate;

    @Column(name = "diet_calendar_date")
    private Date dietCalendarDate;

    @Column(name = "diet_update_date")
    @Nullable
    private Date dietUpdateDate;

    @Column(name = "diet_category")
    @Nullable
    private String dietCategory;

    @Column(name = "diet_IMG")
    private String dietImg;

    @Column(name = "diet_memo")
    private String dietMemo;

    public Diet() {
    }

    public Diet(Integer dietNo, @Nullable String dietName, @Nullable Integer userNo, @Nullable Double totalKcal, @Nullable Double totalCarbohydrate, @Nullable Double totalProtein, @Nullable Double totalProvince, @Nullable Double totalSalt, @Nullable String ingredientName, @Nullable String dietStatus, @Nullable Date dietRegistDate, Date dietCalendarDate, @Nullable Date dietUpdateDate, @Nullable String dietCategory, String dietImg, String dietMemo) {
        this.dietNo = dietNo;
        this.dietName = dietName;
        this.userNo = userNo;
        this.totalKcal = totalKcal;
        this.totalCarbohydrate = totalCarbohydrate;
        this.totalProtein = totalProtein;
        this.totalProvince = totalProvince;
        this.totalSalt = totalSalt;
        this.ingredientName = ingredientName;
        this.dietStatus = dietStatus;
        this.dietRegistDate = dietRegistDate;
        this.dietCalendarDate = dietCalendarDate;
        this.dietUpdateDate = dietUpdateDate;
        this.dietCategory = dietCategory;
        this.dietImg = dietImg;
        this.dietMemo = dietMemo;
    }

    public Integer getDietNo() {
        return dietNo;
    }

    public void setDietNo(Integer dietNo) {
        this.dietNo = dietNo;
    }

    @Nullable
    public String getDietName() {
        return dietName;
    }

    public void setDietName(@Nullable String dietName) {
        this.dietName = dietName;
    }

    @Nullable
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(@Nullable Integer userNo) {
        this.userNo = userNo;
    }

    @Nullable
    public Double getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(@Nullable Double totalKcal) {
        this.totalKcal = totalKcal;
    }

    @Nullable
    public Double getTotalCarbohydrate() {
        return totalCarbohydrate;
    }

    public void setTotalCarbohydrate(@Nullable Double totalCarbohydrate) {
        this.totalCarbohydrate = totalCarbohydrate;
    }

    @Nullable
    public Double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(@Nullable Double totalProtein) {
        this.totalProtein = totalProtein;
    }

    @Nullable
    public Double getTotalProvince() {
        return totalProvince;
    }

    public void setTotalProvince(@Nullable Double totalProvince) {
        this.totalProvince = totalProvince;
    }

    @Nullable
    public Double getTotalSalt() {
        return totalSalt;
    }

    public void setTotalSalt(@Nullable Double totalSalt) {
        this.totalSalt = totalSalt;
    }

    @Nullable
    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(@Nullable String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Nullable
    public String getDietStatus() {
        return dietStatus;
    }

    public void setDietStatus(@Nullable String dietStatus) {
        this.dietStatus = dietStatus;
    }

    @Nullable
    public Date getDietRegistDate() {
        return dietRegistDate;
    }

    public void setDietRegistDate(@Nullable Date dietRegistDate) {
        this.dietRegistDate = dietRegistDate;
    }

    public Date getDietCalendarDate() {
        return dietCalendarDate;
    }

    public void setDietCalendarDate(Date dietCalendarDate) {
        this.dietCalendarDate = dietCalendarDate;
    }

    @Nullable
    public Date getDietUpdateDate() {
        return dietUpdateDate;
    }

    public void setDietUpdateDate(@Nullable Date dietUpdateDate) {
        this.dietUpdateDate = dietUpdateDate;
    }

    @Nullable
    public String getDietCategory() {
        return dietCategory;
    }

    public void setDietCategory(@Nullable String dietCategory) {
        this.dietCategory = dietCategory;
    }

    public String getDietImg() {
        return dietImg;
    }

    public void setDietImg(String dietImg) {
        this.dietImg = dietImg;
    }

    public String getDietMemo() {
        return dietMemo;
    }

    public void setDietMemo(String dietMemo) {
        this.dietMemo = dietMemo;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "dietNo=" + dietNo +
                ", dietName='" + dietName + '\'' +
                ", userNo=" + userNo +
                ", totalKcal=" + totalKcal +
                ", totalCarbohydrate=" + totalCarbohydrate +
                ", totalProtein=" + totalProtein +
                ", totalProvince=" + totalProvince +
                ", totalSalt=" + totalSalt +
                ", ingredientName='" + ingredientName + '\'' +
                ", dietStatus='" + dietStatus + '\'' +
                ", dietRegistDate=" + dietRegistDate +
                ", dietCalendarDate=" + dietCalendarDate +
                ", dietUpdateDate=" + dietUpdateDate +
                ", dietCategory='" + dietCategory + '\'' +
                ", dietImg=" + dietImg +
                ", dietMemo='" + dietMemo + '\'' +
                '}';
    }
}
