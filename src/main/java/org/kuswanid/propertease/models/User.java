package org.kuswanid.propertease.models;

public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private String role;

    public User(int id, String email, String name, String password, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
