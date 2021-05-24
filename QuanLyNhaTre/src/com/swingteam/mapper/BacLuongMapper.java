/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.mapper;

import com.swingteam.model.BacLuongModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class BacLuongMapper implements RowMapper<BacLuongModel>{

    @Override
    public BacLuongModel mapRow(ResultSet rs) {
        try {
            BacLuongModel bl = new BacLuongModel();
            bl.setMaBacLuong(rs.getString("maBacLuong"));
            bl.setHeSoBac(rs.getInt("heSoBac"));
            bl.setMucLuongCanBan(rs.getLong("mucLuongCanBan"));
            bl.setTongLuong(rs.getLong("TongLuong"));
            
            return bl;
        } catch (Exception e) {
            return null;
        }
    }
    
}
