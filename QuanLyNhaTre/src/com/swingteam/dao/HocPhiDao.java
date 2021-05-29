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
                hocPhi.setMaHP(rs.getString("soBienLaiThuTien"));
                hocPhi.setNgayThu(rs.getString("NgayThu"));
                hocPhi.setTienHP(rs.getDouble("TienHocPhi"));
                hocPhi.setTienDaDong(rs.getDouble("TienDaDong"));
                hocPhi.setTtrang(rs.getString("TinhTRang"));
                hocPhi.setMaTre(rs.getString("Matre"));
                hocPhi.setManv(rs.getString("MANHANVIEN"));
                hPhi.add(hocPhi);
            }
        } catch (Exception ex) {
            Logger.getLogger(HocPhiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hPhi;
    }
    public void addHocPHi(HocPhiModel hPhi){
        Connection connection=SwinTeamConnect.SQLConnect();
        String sql="SET DATEFORMAT dmy INSERT INTO TreDongHocPhi( soBienLaiThuTien, ngayThu, tienHocPhi, tienDaDong, tinhTrang, maTre, MANHANVIEN) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, hPhi.getMaHP());
            preparedStatement.setString(2, hPhi.getNgayThu());
            preparedStatement.setDouble(3, hPhi.getTienHP());
            preparedStatement.setDouble(4, hPhi.getTienDaDong());
            preparedStatement.setString(5, hPhi.getTtrang());
            preparedStatement.setString(6, hPhi.getMaTre());
            preparedStatement.setString(7, hPhi.getManv());
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
            preparedStatement.setString(1, hPhi.getNgayThu());
            preparedStatement.setDouble(2, hPhi.getTienHP());
            preparedStatement.setDouble(3, hPhi.getTienDaDong());
            preparedStatement.setString(4, hPhi.getTtrang());
            preparedStatement.setString(5, hPhi.getMaHP());
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
                hocPhi.setMaHP(rs.getString("soBienLaiThuTien"));
                hocPhi.setNgayThu(rs.getString("NgayThu"));
                hocPhi.setTienHP(rs.getDouble("TienHocPhi"));
                hocPhi.setTienDaDong(rs.getDouble("TienDaDong"));
                hocPhi.setTtrang(rs.getString("TinhTRang"));
                hocPhi.setMaTre(rs.getString("Matre"));
                hocPhi.setManv(rs.getString("MANHANVIEN"));
                 return hocPhi;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(HocPhiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
