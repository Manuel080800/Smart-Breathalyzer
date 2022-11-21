package com.breathalyzer.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {
    
    public final String URL;
    public final String USER;
    public final String PASSWORD;
    private Connection connection;

    public MariaDB() {
        URL = "jdbc:mariadb://localhost:3306/breathalyzer";
        USER = "breathalyzer";
        PASSWORD = "12345";
    }
 
    public void openDatabaseConnection() throws SQLException{
        System.out.println("Opening database connection...");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connection valid: " + connection.isValid(0));
    }
 
    public void closeDatabaseConnection() throws SQLException {
        System.out.println("Clossing database connection...");
        connection.close();
        System.out.println("Connection valid: " + connection.isValid(0));
    }

    public Connection getConnection() {
        return connection;
    }
}
