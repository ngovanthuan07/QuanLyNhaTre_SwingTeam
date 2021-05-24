/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao.impl;

import com.swingteam.dao.INhanVienDAO;
import com.swingteam.mapper.NhanVienMapper;
import com.swingteam.model.NhanVienModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class NhanVienDao extends AbstractDAO<NhanVienModel> implements INhanVienDAO{

    @Override
    public List<com.swingteam.model.NhanVienModel> findAll() {
        String sql = "select * from NhanVien as nv, BacLuong as bl, ChucVu as cv\n"
                + "where nv.maBacLuong = bl.maBacLuong and nv.maChucVu = cv.maChucVu";
        
        return query(sql, new NhanVienMapper());
    }
    
}
