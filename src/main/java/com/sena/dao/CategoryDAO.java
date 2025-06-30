package com.sena.dao;

import com.sena.model.Category;
import com.sena.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CategoryDAO {

    private static final Logger LOGGER = Logger.getLogger(CategoryDAO.class.getName());

    // Insert
    public boolean insert(Category category) {
        String sql = "INSERT INTO category (name, description) VALUES (?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return false;
        }
    }

    // Find All (only not soft-deleted)
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE deleted_at IS NULL";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("deleted_at")
                ));
            }

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }

        return categories;
    }

    // Find by ID (only if not soft-deleted)
    public Category findById(int id) {
        String sql = "SELECT * FROM category WHERE id = ? AND deleted_at IS NULL";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("deleted_at")
                );
            }

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }

        return null;
    }

    // Update
    public boolean update(Category category) {
        String sql = "UPDATE category SET name = ?, description = ? WHERE id = ? AND deleted_at IS NULL";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return false;
        }
    }

    // Soft Delete (set deleted_at to current timestamp)
    public boolean softDelete(int id) {
        String sql = "UPDATE category SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return false;
        }
    }
}
