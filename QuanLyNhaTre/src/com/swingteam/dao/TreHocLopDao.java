/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;

import com.swingteam.SQLconnect.SwinTeamConnect;
import com.swingteam.model.TreHocLopModel;
import com.swingteam.model.TreModel;
import java.io.IOException;
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
public class TreHocLopDao {
    public List<TreHocLopModel> getTreHocLop(){
    List<TreHocLopModel> tres = new ArrayList<TreHocLopModel>();
    Connection connection = SwinTeamConnect.SQLConnect();
    
    String sql = "SELECT * FROM TreHocLop";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                TreHocLopModel tre= new TreHocLopModel();
                tre.setManh(rs.getString("MANAMHOC"));
                tre.setMalop(rs.getString("MALOP"));
                 tre.setMatre(rs.getString("MATRE"));
                tres.add(tre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreHocLopDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tres;
}
    public void addTreHocLop(TreHocLopModel tre){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="INSERT INTO TreHocLop( MANAMHOC, MALOP, MATRE) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, tre.getManh());
            preparedStatement.setString(2, tre.getMalop());
            preparedStatement.setString(3, tre.getMatre());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TreHocLopDao.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    public void updateTreHocLop(TreHocLopModel tre){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="UPDATE  TreHocLop SET MALOP=?  WHERE MATRE =?";
               
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);      
            preparedStatement.setString(1, tre.getMalop());
            preparedStatement.setString(2, tre.getMatre());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TreHocLopDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void deleteTreHocLop(String matre){
        Connection connection = SwinTeamConnect.SQLConnect();
        String sql= "DELETE TreHocLop TRE where MATRE = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matre);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TreHocLopDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public TreHocLopModel getTreByMa(String ma) throws IOException, SQLException{
        Connection connection = SwinTeamConnect.SQLConnect();
         String sql = "SELECT * FROM TreHocLop WHERE MATRE = ?";
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                 TreHocLopModel tre=new TreHocLopModel();
                 tre.setManh(rs.getString("MANAMHOC"));
                tre.setMatre(rs.getString("MALOP"));
                 tre.setMatre(rs.getString("MATRE"));
                 return tre;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<TreModel> searchtre(String malop) throws SQLException{
        List<TreModel> tres = new ArrayList<TreModel>();
        Connection connection = SwinTeamConnect.SQLConnect();
        String sql = "select tre.matre, hoTen  from tre,TreHocLop,Lop where tre.maTre=TreHocLop.maTre and TreHocLop.maLop=Lop.maLop and lop.maLop='"+malop+"'";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                TreModel tre = new TreModel();
                tre.setMatre(rs.getString("MATRE"));
                tre.setTentre(rs.getString("HOTEN"));  
                tres.add(tre); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tres;
    }
}
