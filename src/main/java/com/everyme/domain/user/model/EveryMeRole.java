package com.everyme.domain.user.model;

public enum EveryMeRole {

    USER("USER"),
    ADMIN("ADMIN"),
    ALL("USER,ADMIN");

    private String role;

    EveryMeRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }


    @Override
    public String toString() {
        return role;
    }
}
