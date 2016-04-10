package API;

import API.ConnectToDb;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public abstract class InsertIntoDb {
    public static void Insert(String sql) {
        Connection conn = ConnectToDb.getConn();
        Statement stmt = ConnectToDb.getStmt();
        try {
        	stmt.executeUpdate(sql);
            GUI.Grafika.StatusBar.setText("Inserted successfully"); 
            System.out.println("Record added successfully");
        }
        catch (SQLException e) {
        	GUI.Grafika.StatusBar.setText("Insert not successful");
            
        }
       
    }
}