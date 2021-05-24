/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;
import com.swingteam.SQLconnect.HieuConnect;
import com.swingteam.model.TreModel;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class TreDao {
    public List<TreModel> getTre(){
    List<TreModel> tres = new ArrayList<TreModel>();
    Connection connection = HieuConnect.SQLConnect();
    
    String sql = "SELECT * FROM TRE";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                TreModel tre= new TreModel();
                tre.setMatre(rs.getString("MATRE"));
                tre.setTentre(rs.getString("HOTEN"));
                tre.setNgaysinh(rs.getString("NGAYSINH"));
                tre.setGioi(rs.getByte("GIOITINH"));
                tre.setMaph(rs.getString("MAPHUHUYNH"));
                tres.add(tre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tres;
}
     public void addTre(TreModel tre){
        Connection connection=HieuConnect.SQLConnect();
        String sql="INSERT INTO TRE( MATRE, HOTEN, NGAYSINH, GIOITINH, MAPHUHUYNH) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, tre.getMatre());
            preparedStatement.setString(2, tre.getTentre());
            preparedStatement.setString(3, tre.getNgaysinh());
            preparedStatement.setByte(4, tre.getGioi());
            preparedStatement.setString(5, tre.getMaph());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
     
     public void updateTre(TreModel tre){
        Connection connection=HieuConnect.SQLConnect();
        String sql="UPDATE  TRE SET HOTEN= ?, NGAYSINH= ?,  GIOITINH= ?, MAPHUHUYNH= ? WHERE MATRE =?";
               
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);      
            preparedStatement.setString(1,tre.getTentre());
            preparedStatement.setString(2, tre.getNgaysinh());
            preparedStatement.setByte(3, tre.getGioi());
            preparedStatement.setString(4, tre.getMaph());
            preparedStatement.setString(5, tre.getMatre());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
     public void deleteTre(String matre){
        Connection connection = HieuConnect.SQLConnect();
        String sql= "DELETE FROM TRE where MATRE = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matre);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public TreModel getTreByMa(String ma) throws IOException, SQLException{
        Connection connection = HieuConnect.SQLConnect();
         String sql = "SELECT * FROM TRE WHERE MATRE = ?";
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                 TreModel tre=new TreModel();
                 tre.setMatre(rs.getString("MATRE"));
                 tre.setTentre(rs.getString("HOTEN"));
                 tre.setNgaysinh(rs.getString("NGAYSINH"));
                 tre.setGioi(rs.getByte("GIOITINH"));
                 tre.setMaph(rs.getString("MAPHUHUYNH"));
                 return tre;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public List<TreModel> searchTreByTen(String tentre) throws SQLException{
        List<TreModel> tres = new ArrayList<TreModel>();
        Connection connection = HieuConnect.SQLConnect();
        String sql = "SELECT * FROM TRE WHERE HOTEN like N'%"+tentre+"%'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                TreModel tre = new TreModel();
                tre.setMatre(rs.getString("MATRE"));
                tre.setTentre(rs.getString("HOTEN"));
                tre.setNgaysinh(rs.getString("NGAYSINH"));
                tre.setGioi(rs.getByte("GIOITINH"));
                tre.setMaph(rs.getString("MAPHUHUYNH"));   
                tres.add(tre); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tres;
    }
     
      public List<TreModel> searchTreByMatre(String matre) throws SQLException{
        List<TreModel> tres = new ArrayList<TreModel>();
        Connection connection = HieuConnect.SQLConnect();
        String sql = "SELECT * FROM TRE WHERE MATRE like N'%"+matre+"%'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                TreModel tre = new TreModel();
                tre.setMatre(rs.getString("MATRE"));
                tre.setTentre(rs.getString("HOTEN"));
                tre.setNgaysinh(rs.getString("NGAYSINH"));
                tre.setGioi(rs.getByte("GIOITINH"));
                tre.setMaph(rs.getString("MAPHUHUYNH"));   
                tres.add(tre); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tres;
    }
}
