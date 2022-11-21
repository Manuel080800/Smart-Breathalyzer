package com.breathalyzer.tool;

import com.breathalyzer.mariadb.CRUD;
import com.breathalyzer.mariadb.MariaDB;
import com.breathalyzer.model.Register;
import java.sql.SQLException;
import java.util.ArrayList;

public class Local {
    
    private static MariaDB connection;
    private static CRUD database;

    public Local() {
        connection = new MariaDB();
    }
    
    public void initLocal() throws SQLException {
        connection.openDatabaseConnection();
        database = new CRUD(connection.getConnection());
    }
    
    public ArrayList <Register> readRegister() {
        try {
            return database.readData();
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public boolean createdRegister(Register register) {
        try {
            database.createData(register);
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
    
    public boolean updateRegister(Register register) {
        try {
            database.updateData(register);
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
    
    public boolean removeRegister(Register register) {
        try {
            database.deleteData(register.getId());
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
    
    public boolean truncateTable() {
        try {
            database.truncateTable();
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
    
    public void closeLocal() throws SQLException {
        connection.closeDatabaseConnection();
    }
    
}
