/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.SQLconnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class SwinTeamConnect {
    public static String user="sa";
    public static String password="swingteam";
    
    public static Connection SQLConnect(){// connect cua Nguyen Minh Hieu

        final String url="jdbc:sqlserver://localhost:1433;databaseName=QUANLYNHATRE;user="+user+";password="+password;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                return DriverManager.getConnection(url);
            } catch (SQLException ex) {
               
            }
        } catch (ClassNotFoundException ex) {
            
        }
        return null;
    }
    public Connection getConnection() throws SQLException {// connect con Ngo Van Thuan
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYNHATRE";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    public static Connection openConnection() {
        Connection conn;
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QUANLYNHATRE;"
                    + "username=sa;password=123456789");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return null;
    }

    public static void closeAll(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
