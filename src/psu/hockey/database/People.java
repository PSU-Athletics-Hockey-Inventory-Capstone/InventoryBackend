/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psu.hockey.database;

/**
 *
 * @author Kevin
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class People {
    private static final Connection connection = DBConnection.getConnection();
    private static PreparedStatement addManager;
    private static PreparedStatement addPlayer;
    
    public static int addManager(String firstName, String lastName, String psuid, String psuemail){
        int result = 0;
        try {
            addManager = connection.prepareStatement("INSERT INTO manager (FIRSTNAME, LASTNAME, PSUID, PSUEMAIL) VALUES (?, ?, ?, ?)");
            addManager.setString(1, firstName);
            addManager.setString(2, lastName);
            addManager.setString(3, psuid);
            addManager.setString(4, psuemail);
            result = addManager.executeUpdate();
            System.out.println("Manager added");
        } catch (SQLException ex) {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
        public static int addPlayer(int number, String name, String position){
        int result = 0;
        try {
            addPlayer = connection.prepareStatement("INSERT INTO player (NUMBER, NAME, POSITION) VALUES (?, ?, ?)");
            addPlayer.setInt(1, number);
            addPlayer.setString(2, name);
            addPlayer.setString(3, position);
            result = addPlayer.executeUpdate();
            System.out.println("Player added");
        } catch (SQLException ex) {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
