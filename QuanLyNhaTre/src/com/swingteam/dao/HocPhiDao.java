/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;

import com.swingteam.SQLconnect.SwinTeamConnect;
import com.swingteam.model.HocPhiModel;
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
public class HocPhiDao {
    public List<HocPhiModel> getHocPHi(){
        List<HocPhiModel> hPhi= new ArrayList<HocPhiModel>();
        Connection connection= SwinTeamConnect.SQLConnect();
        
        String sql= "select * from TreDongHocPhi";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                HocPhiModel hocPhi=new HocPhiModel();
                hocPhi.setMaHP(rs.getString("MaHocPhi"));
                hocPhi.setNgayThu(rs.getString("NgayThu"));
                hocPhi.setTienHP(rs.getLong("TienHocPhi"));
                hocPhi.setTienDaDong(rs.getLong("TienDaDong"));
                hocPhi.setTtrang(rs.getString("TinhTRang"));
                hocPhi.setMaTre(rs.getString("Matre"));
                hocPhi.setMaPH(rs.getString("MaPhuHuynh"));
                hPhi.add(hocPhi);
            }
        } catch (Exception ex) {
            Logger.getLogger(HocPhiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hPhi;
    }
    public void addHocPHi(HocPhiModel hPhi){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="SET DATEFORMAT dmy INSERT INTO TreDongHocPhi( soBienLaiThuTien, ngayThu, tienHocPhi, tienDaDong, tinhTrang, maTre, maPhuHuynh) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, hPhi.getMaHP());
            preparedStatement.setString(2, hPhi.getNgayThu());
            preparedStatement.setLong(3, hPhi.getTienHP());
            preparedStatement.setLong(4, hPhi.getTienDaDong());
            preparedStatement.setString(5, hPhi.getTtrang());
            preparedStatement.setString(5, hPhi.getMaTre());
            preparedStatement.setString(5, hPhi.getMaPH());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HocPhiDao.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
     
     public void updateHocPhi(HocPhiModel hPhi){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="SET DATEFORMAT dmy UPDATE  TreDongHocPhi SET ngayThu= ?,   tienHocPhi= ?, tienDaDong= ?, tinhTrang= ? WHERE soBienLaiThuTien=?";
               
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);      
            preparedStatement.setString(2, hPhi.getNgayThu());
            preparedStatement.setLong(3, hPhi.getTienHP());
            preparedStatement.setLong(4, hPhi.getTienDaDong());
            preparedStatement.setString(5, hPhi.getTtrang());
            preparedStatement.setString(1, hPhi.getMaHP());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HocPhiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
     public void deleteHocPhi(String MaHP){
        Connection connection = SwinTeamConnect.SQLConnect();
        String sql= "DELETE FROM TreDongHocPhi where soBienLaiThuTien= ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MaHP);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(HocPhiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public HocPhiModel getHocPhiByMa(String maHP) throws IOException, SQLException{
        Connection connection = SwinTeamConnect.SQLConnect();
         String sql = "SELECT * FROM TreDongHocPhi WHERE soBienLaiThuTien = ?";
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, maHP);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                HocPhiModel hocPhi=new HocPhiModel();
                hocPhi.setMaHP(rs.getString("MaHocPhi"));
                hocPhi.setNgayThu(rs.getString("NgayThu"));
                hocPhi.setTienHP(rs.getLong("TienHocPhi"));
                hocPhi.setTienDaDong(rs.getLong("TienDaDong"));
                hocPhi.setTtrang(rs.getString("TinhTRang"));
                hocPhi.setMaTre(rs.getString("Matre"));
                hocPhi.setMaPH(rs.getString("MaPhuHuynh"));
                 return hocPhi;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HocPhiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
