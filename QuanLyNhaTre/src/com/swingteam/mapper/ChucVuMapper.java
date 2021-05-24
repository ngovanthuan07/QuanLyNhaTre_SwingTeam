/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.mapper;

import com.swingteam.model.ChucVuModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class ChucVuMapper implements RowMapper<ChucVuModel>{

    @Override
    public ChucVuModel mapRow(ResultSet rs) {
        try {
            ChucVuModel cv = new ChucVuModel();
            cv.setMaChucVu(rs.getString(""));
            cv.setTenChucVu(rs.getString(""));
            return cv;
        } catch (Exception e) {
            return null;
        }
    }
    
}
