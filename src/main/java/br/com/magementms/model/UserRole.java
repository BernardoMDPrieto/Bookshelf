package br.com.magementms.model;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    CUSTOMER("customer");

    private String role;


    UserRole(String role) {
        this.role = role;
    }
}
