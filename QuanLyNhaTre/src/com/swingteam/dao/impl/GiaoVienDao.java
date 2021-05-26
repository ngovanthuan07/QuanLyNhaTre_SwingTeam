/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao.impl;

import com.swingteam.dao.IGiaoVienDAO;
import com.swingteam.mapper.GiaoVienMapper;
import com.swingteam.model.GiaoVienModel;
import java.util.List;

/**
 *
 * @author hungtr
 */
public class GiaoVienDao extends AbstractDAO<GiaoVienModel> implements IGiaoVienDAO {

    @Override
    public List<com.swingteam.model.GiaoVienModel> findAll() {
        String sql = "select * from GiaoVien as gv, BacLuong as bl\n"
                + "where gv.maBacLuong = bl.maBacLuong and gv.trangThai = 1";
        List<GiaoVienModel> listGiaoVien = query(sql, new GiaoVienMapper());
        return listGiaoVien.isEmpty() ? null : listGiaoVien;
    }

    @Override
    public String save(GiaoVienModel giaoVienModel) {
        String sql = "insert into GiaoVien(maGiaoVien,tenGiaoVien,ngaySinh,cmnd,ngayVaoLam,trangThai,gioiTinh,diaChi,soDienThoai,maBacLuong,ghiChu)\n"
                + "	values(?,?,?,?,?,?,?,?,?,?,?)";

        String addGV = (String) insert(sql, "", 
                giaoVienModel.getTenGiaoVien(), 
                giaoVienModel.getNgaySinh(),  
                giaoVienModel.getCmnd(), 
                giaoVienModel.getNgayVaoLam(), 
                giaoVienModel.getGioiTinh(),
                giaoVienModel.getTrangThai(),
                giaoVienModel.getDiaChi(), 
                giaoVienModel.getSoDienThoai(),
                giaoVienModel.getMaBacLuong(),  
                giaoVienModel.getGhiChu());
        return addGV;
    }

}
