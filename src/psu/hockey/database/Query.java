package psu.hockey.database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Query {
    private static final Connection connection = DBConnection.getConnection();
    private static PreparedStatement queryInventory;
    private static PreparedStatement queryManagers;
    private static PreparedStatement queryPlayers;
    
    public static int QueryInventory(){
        int result = 0;
        String text = "Barcode| Brand|  Model|  In/Out      | Owner     |\n"
                    + "--------------------------------------------------\n";
        try{
            queryInventory = connection.prepareStatement("SELECT * FROM inventory");
            //Grab all rows in inventory
            ResultSet inventory = queryInventory.executeQuery();
            //While there is more to be read
            while(inventory.next()){
                text += inventory.getString("BARCODE")  + "\t";
                text += inventory.getString("BRAND")    + "\t";
                text += inventory.getString("Model")    + "\t";
                if(inventory.getBoolean("CHECKEDIN"))
                    text += "Checked in" + "\t";
                else
                    text += "Checked out" + "\t";
                text += inventory.getString("OWNER") + "\n";
            }
        }catch(SQLException e){
            
        }
        System.out.println(text);
        return result;
    }
    
    public static int QueryManagers(){
        int result = 0;
        String text = "";
        try{
            queryManagers = connection.prepareStatement("SELECT * FROM manager");
            ResultSet managers = queryManagers.executeQuery();
            text = "First Name   |  Last Name    |  PSU ID      |   PSU Email   |\n"
                        + "---------------------------------------------------------\n";
            while(managers.next()){
                text += managers.getString("FIRSTNAME")+"\t\t";
                text += managers.getString("LASTNAME")+"\t\t";
                text += managers.getString("PSUID")+"\t";
                text += managers.getString("PSUEMAIL")+"\n";
            }
        }catch(SQLException e){
            
        }
        System.out.println(text);
        return result;
    }
    
    public static int QueryPlayers(){
        int result = 0;
        String text = "";
        try{
            queryPlayers = connection.prepareStatement("SELECT * FROM player");
            ResultSet players = queryPlayers.executeQuery();
            text = "Name        |   Number  |   Position\n"
                 + "------------------------------------\n";
            while(players.next()){
                text += players.getString("NAME")+"\t";
                text += players.getInt("NUMBER")+"\t";
                text += players.getString("POSITION")+"\n";
            }
        }catch(SQLException e){
            
        }
        System.out.println(text);
        return result;
    }
}
