package com.breathalyzer.mariadb;

import com.breathalyzer.model.Register;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUD {
    
    private final Connection connection;

    public CRUD(Connection connection) {
        this.connection = connection;
    }
    
    public void createData (Register register) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO `daily-analysis` (`id`, `name`, `sex`, `curp`, `plate`, `alcohol`, `temperature`, `viability`, `sense`, `registration`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setNull(1, java.sql.Types.NULL);
        statement.setString(2, register.getName());
        statement.setString(3, register.getSex());
        statement.setString(4, register.getCurp());
        statement.setString(5, register.getPlate());
        statement.setDouble(6, register.getAlcohol());
        statement.setDouble(7, register.getTemperature());
        statement.setString(8, register.getViability());
        statement.setString(9, register.getSense());
        statement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
        
        int rowsInserted = statement.executeUpdate();
        System.out.println("Rows inserted: " + rowsInserted);
    }
    
    public ArrayList <Register> readData () throws SQLException {
        ArrayList <Register> data = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `daily-analysis` ORDER BY `daily-analysis`.`registration` DESC");
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            String curp = resultSet.getString("curp");
            String plate = resultSet.getString("plate");
            double alcohol = resultSet.getDouble("alcohol");
            double temperature = resultSet.getDouble("temperature");
            String viability = resultSet.getString("viability");
            String sense = resultSet.getString("sense");
            String registration = resultSet.getString("registration");
            
            data.add(new Register(id, name, sex, curp, plate, alcohol, temperature, viability, sense, registration));
        }
        
        System.out.println("Rows readed: " + data.size());
        return data;
    }
  
    public void updateData(Register register) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE `daily-analysis` SET `name` = ?, `sex` = ?, `curp` = ?, `plate` = ?, `alcohol` = ?, `temperature` = ?, `viability` = ?, `sense` = ?, `registration` = ? WHERE `daily-analysis`.`id` = ?");
        statement.setString(1, register.getName());
        statement.setString(2, register.getSex());
        statement.setString(3, register.getCurp());
        statement.setString(4, register.getPlate());
        statement.setDouble(5, register.getAlcohol());
        statement.setDouble(6, register.getTemperature());
        statement.setString(7, register.getViability());
        statement.setString(8, register.getSense());
        statement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
        statement.setInt(10, register.getId());

        int rowsUpdated = statement.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated);
    }
   
    public void deleteData(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM `daily-analysis` WHERE `daily-analysis`.`id` = ?");
        statement.setInt(1, id);
        
        int rowsDeleted = statement.executeUpdate();
        System.out.println("Rows deleted: " + rowsDeleted);
    }
    
    public void truncateTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE `breathalyzer`.`daily-analysis`");
        int rowsDeleted = statement.executeUpdate();
        System.out.println("Rows truncated: " + rowsDeleted);
    }
    
//    public static void main(String[] args) {
//        try {
//            MariaDB db = new MariaDB();
//            db.openDatabaseConnection();
//            CRUD crub = new CRUD(db.getConnection());
//            crub.truncateTable();
//            db.closeDatabaseConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(MariaDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    
}

