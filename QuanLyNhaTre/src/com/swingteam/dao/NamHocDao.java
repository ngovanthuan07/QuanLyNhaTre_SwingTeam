/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;

import com.swingteam.SQLconnect.SwinTeamConnect;
import com.swingteam.model.NamhocModel;
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
public class NamHocDao {
    public List<NamhocModel> getNH(){
    List<NamhocModel> nhs = new ArrayList<NamhocModel>();
    Connection connection = SwinTeamConnect.SQLConnect();
    
    String sql = "SELECT * FROM NAMHOC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                NamhocModel nh= new NamhocModel();
                nh.setManh(rs.getString("MANAMHOC"));
                nh.setTennh(rs.getString("TENNAMHOC"));
                nhs.add(nh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NamHocDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nhs;
}
    public void addNH(NamhocModel nh){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="INSERT INTO NAMHOC(MANAMHOC, TENNAMHOC) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, nh.getManh());
            preparedStatement.setString(2, nh.getTennh());         
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(NamHocDao.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
