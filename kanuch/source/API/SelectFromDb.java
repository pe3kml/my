package API;


import API.ConnectToDb;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectFromDb {
    public static ResultSet select(String abc) {
    	ResultSet result = null ;
        Connection conn = ConnectToDb.getConn();
        Statement stmt = ConnectToDb.getStmt();
        try {
          result = stmt.executeQuery(abc);
          GUI.Grafika.StatusBar.setText("Selected successfully");
        }
        catch (SQLException e) {
        	GUI.Grafika.StatusBar.setText("Select not successful");
        }
        
        return result;
    }
}
