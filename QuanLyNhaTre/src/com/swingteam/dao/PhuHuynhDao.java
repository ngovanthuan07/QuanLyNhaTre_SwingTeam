/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;
import com.swingteam.SQLconnect.SwinTeamConnect;
import com.swingteam.model.PhuHuynhModel;
import com.swingteam.model.TreModel;
import java.io.IOException;
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
public class PhuHuynhDao {
    public List<PhuHuynhModel> getPH(){
    List<PhuHuynhModel> phs = new ArrayList<PhuHuynhModel>();
    Connection connection = SwinTeamConnect.SQLConnect();
    
    String sql = "SELECT * FROM PHUHUYNH";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                PhuHuynhModel ph= new PhuHuynhModel();
                ph.setMaph(rs.getString("MAPHUHUYNH"));
                ph.setTencha(rs.getString("TENCHA"));
                ph.setTenme(rs.getString("TENME"));
                ph.setNghecha(rs.getString("NGHENGHIEPCHA"));
                ph.setNgheme(rs.getString("NGHENGHIEPME"));
                ph.setSdt(rs.getString("soDienThoaiLienHe"));
                ph.setDiachi(rs.getString("DIACHI"));
                phs.add(ph);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phs;
}
    
    public void addPH(PhuHuynhModel ph){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="INSERT INTO PHUHUYNH( MAPHUHUYNH, TENCHA, TENME, NGHENGHIEPCHA, NGHENGHIEPME, soDienThoaiLienHe, DIACHI) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, ph.getMaph());
            preparedStatement.setString(2, ph.getTencha());
            preparedStatement.setString(3, ph.getTenme());
            preparedStatement.setString(4, ph.getNghecha());
            preparedStatement.setString(5, ph.getNgheme());
            preparedStatement.setString(6, ph.getSdt());
            preparedStatement.setString(7, ph.getDiachi());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PhuHuynhDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
      public void updatePH(PhuHuynhModel ph){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="UPDATE  PHUHUYNH SET TENCHA= ?, TENME= ?,  NGHENGHIEPCHA= ?, NGHENGHIEPME= ?, soDienThoaiLienHe= ?, DIACHI= ? WHERE MAPHUHUYNH =?";
               
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);                 
            preparedStatement.setString(1, ph.getTencha());
            preparedStatement.setString(2, ph.getTenme());
            preparedStatement.setString(3, ph.getNghecha());
            preparedStatement.setString(4, ph.getNgheme());
            preparedStatement.setString(5, ph.getSdt());
            preparedStatement.setString(6, ph.getDiachi());
            preparedStatement.setString(7, ph.getMaph());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PhuHuynhDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
      
      public void deletePH(String maph){
        Connection connection = SwinTeamConnect.SQLConnect();
        String sql= "DELETE FROM PHUHUYNH WHERE MAPHUHUYNH = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maph);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
       public PhuHuynhModel getPHByMa(String ma) throws IOException, SQLException{
        Connection connection = SwinTeamConnect.SQLConnect();
         String sql = "SELECT * FROM PHUHUYNH WHERE MAPHUHUYNH = ?";
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                PhuHuynhModel ph=new PhuHuynhModel();
                ph.setMaph(rs.getString("MAPHUHUYNH"));
                ph.setTencha(rs.getString("TENCHA"));
                ph.setTenme(rs.getString("TENME"));
                ph.setNghecha(rs.getString("NGHENGHIEPCHA"));
                ph.setNgheme(rs.getString("NGHENGHIEPME"));
                ph.setSdt(rs.getString("soDienThoaiLienHe"));
                ph.setDiachi(rs.getString("DIACHI"));
                 return ph;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
       public List<PhuHuynhModel> searchPHByMa(String maph) throws SQLException{
        List<PhuHuynhModel> phs = new ArrayList<PhuHuynhModel>();
        Connection connection = SwinTeamConnect.SQLConnect();
        String sql = "SELECT * FROM PHUHUYNH WHERE MAPHUHUYNH like N'%"+maph+"%'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                PhuHuynhModel ph = new PhuHuynhModel();
                ph.setMaph(rs.getString("MAPHUHUYNH"));
                ph.setTencha(rs.getString("TENCHA"));
                ph.setTenme(rs.getString("TENME"));
                ph.setNghecha(rs.getString("NGHENGHIEPCHA"));
                ph.setNgheme(rs.getString("NGHENGHIEPME"));
                ph.setSdt(rs.getString("soDienThoaiLienHe"));
                ph.setDiachi(rs.getString("DIACHI"));   
                phs.add(ph); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phs;
    }
       
       public List<PhuHuynhModel> getPHCuaTre(String matre) throws SQLException{
        List<PhuHuynhModel> phs = new ArrayList<PhuHuynhModel>();
        Connection connection = SwinTeamConnect.SQLConnect();
        
        String sql="select ph.maPhuHuynh,tencha,tenme,soDienThoaiLienHe,diaChi from PhuHuynh as ph,Tre as tr where ph.maPhuHuynh=tr.maPhuHuynh and maTre='"+matre+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
   
            while(rs.next()){
                
                PhuHuynhModel ph = new PhuHuynhModel();
                ph.setMaph(rs.getString("MAPHUHUYNH"));
                ph.setTencha(rs.getString("TENCHA"));
                ph.setTenme(rs.getString("TENME"));
                ph.setSdt(rs.getString("soDienThoaiLienHe"));
                ph.setDiachi(rs.getString("DIACHI"));   
                phs.add(ph); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phs;
    }
}
