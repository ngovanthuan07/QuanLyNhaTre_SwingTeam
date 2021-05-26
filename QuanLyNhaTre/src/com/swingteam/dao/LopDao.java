/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;

import com.swingteam.SQLconnect.SwinTeamConnect;
import com.swingteam.model.Lopmodel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class LopDao {
    public List<Lopmodel> getLop(){
    List<Lopmodel> lops = new ArrayList<Lopmodel>();
    Connection connection = SwinTeamConnect.SQLConnect();
    
    String sql = "SELECT * FROM LOP";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Lopmodel lop= new Lopmodel();
                lop.setMalop(rs.getString("MALOP"));
                lop.setTenlop(rs.getString("TENLOP"));
                lops.add(lop);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lops;
}
    public void addLop(Lopmodel lop){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="INSERT INTO LOP(MALOP, TENLOP) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, lop.getMalop());
            preparedStatement.setString(2, lop.getTenlop());         
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(LopDao.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
