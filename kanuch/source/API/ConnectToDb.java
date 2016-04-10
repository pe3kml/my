package API;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ConnectToDb {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "123000";
    static Statement stmt;
    static Connection conn;

    static {
        conn = null;
    }

    public static void Connect() {
       //Scanner in = new Scanner(System.in);
        try {

            Class.forName("org.postgresql.Driver");											
            Connection connL = DriverManager.getConnection(DB_URL, USER, PASS);
            connL.setAutoCommit(true);
            Statement stmtL = connL.createStatement();
            ConnectToDb.setConn(connL);
            ConnectToDb.setStmt(stmtL);
            GUI.Grafika.mainPanel.setVisible(true);
            GUI.Grafika.StatusBar.setText("Connected successfully");
			System.out.println("Opened database successfully");
			
			for(int i = 0; i < 7; i++){
			 	GUI.Grafika.date_choice.add(API.Date.getDatumy().get(i).toString());
			 	GUI.Grafika.date_choice2.add(API.Date.getDatumy().get(i).toString());
			};
        }
        catch (Exception e) {
        	GUI.Grafika.StatusBar.setText("Connect not successful");

			/*if (input == JOptionPane.OK_OPTION) {
				System.exit(0);
			}*/
           //System.out.println("Koniec");
            //System.exit(0);
        }
        
    }

    public static void DisConnect() {
        conn = ConnectToDb.getConn();
        stmt = ConnectToDb.getStmt();
        try {
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database disconnect.");
       // System.exit(0);
        
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static void setStmt(Statement stmt) {
        ConnectToDb.stmt = stmt;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        ConnectToDb.conn = conn;
    }
}
