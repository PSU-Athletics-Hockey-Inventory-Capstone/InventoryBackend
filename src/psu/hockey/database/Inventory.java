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

public class Inventory {
    //Create a connection to the database
    private static final Connection connection = DBConnection.getConnection();
    private static PreparedStatement addInventory; //Create a barcode for the item
    private static PreparedStatement checkedin;
    private static PreparedStatement checkedout;
    
    public static int AddInventory(int barcode, String brand, String model, String owner) throws SQLException{
        int result = 0;
        try{
            Timestamp time = new Timestamp(System.currentTimeMillis());
            addInventory = connection.prepareStatement("INSERT INTO inventory (BARCODE, BRAND, MODEL, OWNER, CHECKEDIN, TIMEIN) VALUES (?, ?, ?, ?, ?, ?)");
            addInventory.setInt(1, barcode);
            addInventory.setString(2, brand);
            addInventory.setString(3, model);
            addInventory.setString(4, owner);
            addInventory.setBoolean(5, true); //Assume that once inventory is added its checked in
            addInventory.setTimestamp(6, time);
            result = addInventory.executeUpdate();
        }catch(SQLException sqlException){
            JOptionPane.showMessageDialog(null, "SQL ERROR.");
        }
        return result;
    }
    
    public static int CheckOutInventory(int barcode){
       int result = 0;
        try {
            Timestamp time = new Timestamp(System.currentTimeMillis());
                checkedout = connection.prepareStatement("UPDATE inventory SET CHECKEDIN = ?, TIMEOUT = ? WHERE BARCODE = ?");
                checkedout.setBoolean(1, false);
                checkedout.setTimestamp(2, time);
                checkedout.setInt(3, barcode);
                result = checkedout.executeUpdate();
                System.out.println("Checked Out");
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    
    public static int CheckInInventory(int barcode){
       int result = 0;
        try {
            Timestamp time = new Timestamp(System.currentTimeMillis());
            checkedin = connection.prepareStatement("UPDATE inventory SET CHECKEDIN = ?, TIMEIN = ? WHERE BARCODE = ?");
            checkedin.setBoolean(1, true);
            checkedin.setTimestamp(2, time);
            checkedin.setInt(3, barcode);
            result = checkedin.executeUpdate();
            System.out.println("Checked In");
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
}
