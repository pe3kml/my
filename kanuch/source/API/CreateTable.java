package API;


import API.ConnectToDb;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void Create() {
        Connection conn = null;
        try {
            try {
                conn = ConnectToDb.getConn();
                Statement stmt = ConnectToDb.getStmt();
                String sql = "CREATE TABLE COMPANY (ID INT PRIMARY KEY     NOT NULL, NAME           TEXT    NOT NULL,  AGE            INT     NOT NULL,  ADDRESS        CHAR(50),  SALARY         REAL)";
                stmt.executeUpdate(sql);
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
