/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.mapper;

import com.swingteam.model.NhanVienModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class NhanVienMapper_BacLuong implements RowMapper<NhanVienModel> {

    @Override
    public NhanVienModel mapRow(ResultSet rs) {
        try {
            NhanVienModel nv = new NhanVienModel();
            nv.setMaNhanVien(rs.getString("maNhanVien"));
            nv.setTenNhanVien(rs.getString("tenNhanVien"));
            nv.setMaBacLuong(rs.getString("maBacLuong"));
            nv.setMucLuongCanBan(rs.getLong("mucLuongCanBan"));
            nv.setHeSoBac(rs.getInt("heSoBac"));
            nv.setTongLuong(rs.getLong("tongLuong"));
            
            return nv;
        } catch (Exception e) {
            return null;
        }
    }

}
