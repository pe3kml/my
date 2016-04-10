package API;


import API.ConnectToDb;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class UpdateDb {
    public static void Update(String sql) {
        Connection conn = ConnectToDb.getConn();
        Statement stmt = ConnectToDb.getStmt();
        try {
           // conn.setAutoCommit(false);
            stmt.executeUpdate(sql);
           // conn.setAutoCommit(true);
            GUI.Grafika.StatusBar.setText("Updated successfully");
            System.out.println("Updated successfully.");
        }
        catch (Exception e) {
        	GUI.Grafika.StatusBar.setText("Update not successful");
           
        }
        
    }
}
