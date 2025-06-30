package com.sena.model;

import java.sql.Timestamp;

public class Category {
    private int id;
    private String name;
    private String description;
    private Timestamp deletedAt; // 🆕 Campo para soft delete

    // Constructor completo
    public Category(int id, String name, String description, Timestamp deletedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deletedAt = deletedAt;
    }

    // Constructor para nuevos registros (no eliminados)
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.equals("Hygiene") && !name.equals("Medicine") &&
            !name.equals("Food") && !name.equals("Beverages")) {
            throw new IllegalArgumentException("Invalid category name: " + name);
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}
