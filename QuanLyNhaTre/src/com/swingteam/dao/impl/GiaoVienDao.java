/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao.impl;

import com.swingteam.dao.IGiaoVienDAO;
import com.swingteam.mapper.GiaoVienMapper;
import com.swingteam.mapper.GiaoVienMapper_BacLuong;
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
                + "values(?,?,?,?,?,?,?,?,?,?,?)";
        String addGV = (String) insert(sql,
                giaoVienModel.getMaGiaoVien(),
                giaoVienModel.getTenGiaoVien(),
                giaoVienModel.getNgaySinh(),
                giaoVienModel.getCmnd(),
                giaoVienModel.getNgayVaoLam(),
                giaoVienModel.getTrangThai(),
                giaoVienModel.getGioiTinh(),
                giaoVienModel.getDiaChi(),
                giaoVienModel.getSoDienThoai(),
                giaoVienModel.getMaBacLuong(),
                giaoVienModel.getGhiChu());
        return addGV;
    }

    @Override
    public boolean findByCMND(String cmnd) {
        String sql = "select * from GiaoVien as gv, BacLuong as bl\n"
                + "where gv.maBacLuong = bl.maBacLuong and gv.cmnd = ?";
        List<GiaoVienModel> listGiaoVien = query(sql, new GiaoVienMapper(), cmnd);
        return listGiaoVien.size() > 0 ? true : false;
    }

    @Override
    public boolean findBySDT(String sdt) {
        String sql = "select * from GiaoVien as gv, BacLuong as bl\n"
                + "where gv.maBacLuong = bl.maBacLuong and gv.soDienThoai = ?";
        List<GiaoVienModel> listGiaoVien = query(sql, new GiaoVienMapper(), sdt);
        return listGiaoVien.size() > 0 ? true : false;
    }

    @Override
    public void edit(GiaoVienModel giaoVienModel, String maGiaoVien, int HeSo) {
        String sql = null;
        switch (HeSo) {
            case 1:
                sql = "update GiaoVien set tenGiaoVien = ?,ngaySinh = ?,cmnd = ?,ngayVaoLam = ?,trangThai = ?,gioiTinh = ?,diaChi = ?,soDienThoai = ?,maBacLuong = ?,ghiChu = ?"
                        + " where maGiaoVien = ?";

                update(sql,
                        giaoVienModel.getTenGiaoVien(),
                        giaoVienModel.getNgaySinh(),
                        giaoVienModel.getCmnd(),
                        giaoVienModel.getNgayVaoLam(),
                        giaoVienModel.getTrangThai(),
                        giaoVienModel.getGioiTinh(),
                        giaoVienModel.getDiaChi(),
                        giaoVienModel.getSoDienThoai(),
                        giaoVienModel.getMaBacLuong(),
                        giaoVienModel.getGhiChu(),
                        maGiaoVien);
                break;
            case 2: 
                sql = "update GiaoVien set maBacLuong = ?"
                        + " where maGiaoVien = ?";
                update(sql, giaoVienModel.getMaBacLuong(), maGiaoVien);
                break;
        }
    }

    @Override
    public List<GiaoVienModel> findAllBacLuong_GiaoVien() {
        String sql = "select *, (bl.heSoBac * bl.mucLuongCanBan) as tongLuong from GiaoVien as gv, BacLuong as bl\n"
                + "where gv.maBacLuong = bl.maBacLuong";
        List<GiaoVienModel> listGiaoVien = query(sql, new GiaoVienMapper_BacLuong());
        return listGiaoVien.isEmpty() ? null : listGiaoVien;
    }

}
