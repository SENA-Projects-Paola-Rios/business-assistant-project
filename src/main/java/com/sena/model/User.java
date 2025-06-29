package com.sena.model;

public class User {
    private int idUser;
    private String name;
    private String email;
    private String password;
    private String role;              // Values: "admin", "seller"
    private String accessRestriction; // Optional
    private boolean isDeleted;        // Soft delete flag

    // --- Constructors ---
    public User() {
    }

    public User(int idUser, String name, String email, String password, String role, String accessRestriction, boolean isDeleted) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.accessRestriction = accessRestriction;
        this.isDeleted = isDeleted;
    }

    // --- Getters and Setters ---
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessRestriction() {
        return accessRestriction;
    }

    public void setAccessRestriction(String accessRestriction) {
        this.accessRestriction = accessRestriction;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    // --- Optional: toString for debugging ---
    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
