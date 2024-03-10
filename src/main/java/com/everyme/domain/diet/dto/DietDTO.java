package com.everyme.domain.diet.dto;

public class DietDTO {

    private String dietName;
    private Double totalKcal;
    private String userId;

    private String dietCategory;

    public DietDTO() {
    }

    public DietDTO(String dietName, Double totalKcal, String userId, String dietCategory) {
        this.dietName = dietName;
        this.totalKcal = totalKcal;
        this.userId = userId;
        this.dietCategory = dietCategory;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public Double getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(Double totalKcal) {
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
        return "DietDTO{" +
                "dietName='" + dietName + '\'' +
                ", totalKcal=" + totalKcal +
                ", userId='" + userId + '\'' +
                ", dietCategory='" + dietCategory + '\'' +
                '}';
    }
}
