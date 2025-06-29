package com.sena.dao;

import com.sena.model.User;
import com.sena.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // CREATE
    public boolean insert(User user) {
        String sql = "INSERT INTO usuario (nombre, correo_electronico, contrasena, rol, restriccion_acceso, is_deleted) " +
                     "VALUES (?, ?, ?, ?, ?, false)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getAccessRestriction());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ ALL
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE is_deleted = false";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_usuario"));
                user.setName(rs.getString("nombre"));
                user.setEmail(rs.getString("correo_electronico"));
                user.setPassword(rs.getString("contrasena"));
                user.setRole(rs.getString("rol"));
                user.setAccessRestriction(rs.getString("restriccion_acceso"));
                user.setDeleted(rs.getBoolean("is_deleted"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // READ ONE
    public User getById(int id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ? AND is_deleted = false";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getInt("id_usuario"));
                    user.setName(rs.getString("nombre"));
                    user.setEmail(rs.getString("correo_electronico"));
                    user.setPassword(rs.getString("contrasena"));
                    user.setRole(rs.getString("rol"));
                    user.setAccessRestriction(rs.getString("restriccion_acceso"));
                    user.setDeleted(rs.getBoolean("is_deleted"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE
    public boolean update(User user) {
        String sql = "UPDATE usuario SET nombre = ?, correo_electronico = ?, contrasena = ?, rol = ?, restriccion_acceso = ? " +
                     "WHERE id_usuario = ? AND is_deleted = false";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getAccessRestriction());
            stmt.setInt(6, user.getIdUser());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // SOFT DELETE
    public boolean delete(int id) {
        String sql = "UPDATE usuario SET is_deleted = true WHERE id_usuario = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM usuario WHERE correo_electronico = ? AND contrasena = ? AND is_deleted = false";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getInt("id_usuario"));
                    user.setName(rs.getString("nombre"));
                    user.setEmail(rs.getString("correo_electronico"));
                    user.setPassword(rs.getString("contrasena"));
                    user.setRole(rs.getString("rol"));
                    user.setAccessRestriction(rs.getString("restriccion_acceso"));
                    user.setDeleted(rs.getBoolean("is_deleted"));
                    return user;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
