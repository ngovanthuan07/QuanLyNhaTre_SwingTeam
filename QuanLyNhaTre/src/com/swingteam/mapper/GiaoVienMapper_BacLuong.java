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
 * @author ngova
 */
public class GiaoVienMapper_BacLuong implements RowMapper<GiaoVienModel>{

    @Override
    public GiaoVienModel mapRow(ResultSet rs) {
         try {
            GiaoVienModel nv = new GiaoVienModel();
            nv.setMaGiaoVien(rs.getString("maGiaoVien"));
            nv.setTenGiaoVien(rs.getString("tenGiaoVien"));
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
