package API;


import API.ConnectToDb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DeleteFromDb {
    public static void Delete(String sql) throws SQLException {
        Connection conn = ConnectToDb.getConn();
        Statement stmt = ConnectToDb.getStmt();
        try {
            stmt.executeUpdate(sql);
            GUI.Grafika.StatusBar.setText("Deleted successfully");
            System.out.println("Deleted successfully.");
        }
        catch (SQLException e) {
        	GUI.Grafika.StatusBar.setText("Delet not successful"); e.printStackTrace();

        }
    }
}
