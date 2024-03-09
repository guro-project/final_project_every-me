package com.everyme.domain.diet.dto;

public class DietDTO {

    private String dietName;
    private String totalKcal;

    public DietDTO() {
    }

    public DietDTO(String dietName, String totalKcal) {
        this.dietName = dietName;
        this.totalKcal = totalKcal;
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
        return "DietDTO{" +
                "dietName='" + dietName + '\'' +
                ", totalKcal='" + totalKcal + '\'' +
                '}';
    }
}
