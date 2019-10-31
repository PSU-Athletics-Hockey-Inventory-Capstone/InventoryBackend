package psu.hockey.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hockey";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Panda258038!";
    
    private static Connection connection;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        try{
            if(connection == null){
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.exit(1);
        }
        return connection;
    }
}
