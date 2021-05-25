/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.mapper;

import com.swingteam.model.GiaoVienModel;
import java.sql.ResultSet;

/**
 *
 * @author hungtr
 */
public class GiaoVienMapper implements RowMapper<GiaoVienModel>{
    @Override
    public GiaoVienModel mapRow(ResultSet rs) {
        try {
            GiaoVienModel gv = new GiaoVienModel();
            gv.setMaGiaoVien(rs.getString("maNhanVien"));
            gv.setTenGiaoVien(rs.getString("tenGiaoVien"));
            gv.setNgaySinh(rs.getString("ngaySinh"));
            gv.setCmnd(rs.getInt("cmnd"));
            gv.setNgayVaoLam(rs.getString("ngayVaoLam"));
            gv.setGioiTinh(rs.getInt("gioiTinh"));
            gv.setDiaChi(rs.getString("diaChi"));
            gv.setSoDienThoai(rs.getString("soDienThoai"));
            gv.setGhiChu(rs.getString("ghiChu"));
            gv.setMaBacLuong(rs.getString("maBacLuong"));
            return gv;
        } catch (Exception e) {
            return null;
        }
    }
    
}
