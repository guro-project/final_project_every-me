package com.everyme.domain.diet.dto;

import java.util.Date;

public class DietDTO {

    private String dietName;

    private String userNo;
    private Double totalKcal;
    private Double totalCarbohydrate;
    private Double totalProtein;
    private Double totalProvince;
    private Double totalSalt;
    private String ingredientName;
    private String dietStatus;
    private Date dietRegistDate;
    private Date dietUpdateDate;
    private String dietCategory;

    public DietDTO() {
    }

    public DietDTO(String dietName, String userNo, Double totalKcal, Double totalCarbohydrate, Double totalProtein, Double totalProvince, Double totalSalt, String ingredientName, String dietStatus, Date dietRegistDate, Date dietUpdateDate, String dietCategory) {
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
        this.dietUpdateDate = dietUpdateDate;
        this.dietCategory = dietCategory;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Double getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(Double totalKcal) {
        this.totalKcal = totalKcal;
    }

    public Double getTotalCarbohydrate() {
        return totalCarbohydrate;
    }

    public void setTotalCarbohydrate(Double totalCarbohydrate) {
        this.totalCarbohydrate = totalCarbohydrate;
    }

    public Double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(Double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public Double getTotalProvince() {
        return totalProvince;
    }

    public void setTotalProvince(Double totalProvince) {
        this.totalProvince = totalProvince;
    }

    public Double getTotalSalt() {
        return totalSalt;
    }

    public void setTotalSalt(Double totalSalt) {
        this.totalSalt = totalSalt;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getDietStatus() {
        return dietStatus;
    }

    public void setDietStatus(String dietStatus) {
        this.dietStatus = dietStatus;
    }

    public Date getDietRegistDate() {
        return dietRegistDate;
    }

    public void setDietRegistDate(Date dietRegistDate) {
        this.dietRegistDate = dietRegistDate;
    }

    public Date getDietUpdateDate() {
        return dietUpdateDate;
    }

    public void setDietUpdateDate(Date dietUpdateDate) {
        this.dietUpdateDate = dietUpdateDate;
    }

    public String getDietCategory() {
        return dietCategory;
    }

    public void setDietCategory(String dietCategory) {
        this.dietCategory = dietCategory;
    }

    @Override
    public String toString() {
        return "DietDTO{" +
                "dietName='" + dietName + '\'' +
                ", userNo='" + userNo + '\'' +
                ", totalKcal=" + totalKcal +
                ", totalCarbohydrate=" + totalCarbohydrate +
                ", totalProtein=" + totalProtein +
                ", totalProvince=" + totalProvince +
                ", totalSalt=" + totalSalt +
                ", ingredientName='" + ingredientName + '\'' +
                ", dietStatus='" + dietStatus + '\'' +
                ", dietRegistDate=" + dietRegistDate +
                ", dietUpdateDate=" + dietUpdateDate +
                ", dietCategory='" + dietCategory + '\'' +
                '}';
    }
}
