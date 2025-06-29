package com.sena.dao;

import com.sena.controller.LoginController;
import com.sena.util.ConnectionManager;
import com.sena.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    // Create
    public boolean insert(User user) {
        LOGGER.info("insertando");
        String sql = "INSERT INTO user (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            
            LOGGER.info(stmt.toString());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Read All (only not deleted)
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE deleted_at IS NULL";
        LOGGER.info("list useres");

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getTimestamp("deleted_at")
                ));
            }

        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    // Read by ID
    public User findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ? AND deleted_at IS NULL";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getTimestamp("deleted_at")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update
    public boolean update(User user) {
        LOGGER.info("update method");
        String sql = "UPDATE user SET name = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getId());
            LOGGER.info(stmt.toString());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Soft Delete
    public boolean softDelete(int id) {
        String sql = "UPDATE user SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Validate login
    public User login(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ? AND deleted_at IS NULL";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getTimestamp("deleted_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
