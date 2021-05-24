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
    public static Connection SQLConnect(){
        final String user="sa";
        final String password="minhhieu259";
        final String url="jdbc:sqlserver://localhost:1433;databaseName=QLHang;user="+user+";password="+password;
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
    public Connection getConnection() throws SQLException {// của tau nhé hiếu
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYNHATRE";
            String user = "sa";
            String password = "thuan07";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
