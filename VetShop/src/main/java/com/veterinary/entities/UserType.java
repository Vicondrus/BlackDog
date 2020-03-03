package com.veterinary.entities;

public enum UserType {
    REGULAR("REGULAR"),
    ADMIN("ADMIN");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
