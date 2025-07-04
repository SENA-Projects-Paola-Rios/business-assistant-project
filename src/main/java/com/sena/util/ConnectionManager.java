package com.sena.util;

import com.sena.controller.LoginController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.util.logging.Logger;

public class ConnectionManager {

    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    static {
        try (InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Cannot find config.properties");
            }
            
            prop.load(input);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");           
           

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getLocalizedMessage());
            LOGGER.info(e.getMessage());
            
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
