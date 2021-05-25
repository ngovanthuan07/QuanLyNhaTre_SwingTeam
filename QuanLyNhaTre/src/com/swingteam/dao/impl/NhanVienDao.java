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
public class NhanVienDao extends AbstractDAO<NhanVienModel> implements INhanVienDAO {

    @Override
    public List<com.swingteam.model.NhanVienModel> findAll() {
        String sql = "select * from NhanVien as nv, BacLuong as bl, ChucVu as cv\n"
                + "where nv.maBacLuong = bl.maBacLuong and nv.maChucVu = cv.maChucVu and nv.trangThai = 1";
        List<NhanVienModel> listNhanVien = query(sql, new NhanVienMapper());
        return listNhanVien.isEmpty() ? null : listNhanVien;
    }

    @Override
    public String save(NhanVienModel nhanVienModel) {
        String sql = "insert into NhanVien(maNhanVien,tenNhanVien,ngaySinh,gioiTinh,cmnd,ngayVaoLam,diaChi,soDienThoai,trangThai,ghiChu,maBacLuong,maChucVu)	\n"
                + "	values(?,?,?,?,?,?,?,?,?,?,?,?)";

        String addNV = (String) insert(sql, "",
                nhanVienModel.getTenNhanVien(),
                nhanVienModel.getNgaySinh(),
                nhanVienModel.getGioiTinh(),
                nhanVienModel.getCmnd(),
                nhanVienModel.getNgayVaoLam(),
                nhanVienModel.getDiaChi(),
                nhanVienModel.getSoDienThoai(),
                nhanVienModel.getTrangThai(),
                nhanVienModel.getGhiChu(),
                nhanVienModel.getMaBacLuong(),
                nhanVienModel.getMaChucVu());
        return addNV;
    }

    @Override
    public void edit(NhanVienModel nhanVienModel, String maNhanVien) {
        String sql = "update NhanVien set tenNhanVien = ?, ngaySinh = ?, gioiTinh = ?,cmnd = ?,ngayVaoLam = ?,diaChi = ?,soDienThoai = ?,trangThai = ?,ghiChu = ?,maBacLuong = ? where maNhanVien = ?";

        update(sql,
                nhanVienModel.getTenNhanVien(),
                nhanVienModel.getNgaySinh(),
                nhanVienModel.getGioiTinh(),
                nhanVienModel.getCmnd(),
                nhanVienModel.getNgayVaoLam(),
                nhanVienModel.getDiaChi(),
                nhanVienModel.getSoDienThoai(),
                nhanVienModel.getTrangThai(),
                nhanVienModel.getGhiChu(),
                nhanVienModel.getMaBacLuong(),
                maNhanVien);
    }

    @Override
    public List<NhanVienModel> findAllKhongLam() {
        String sql = "select * from NhanVien as nv, BacLuong as bl, ChucVu as cv\n"
                + "where nv.maBacLuong = bl.maBacLuong and nv.maChucVu = cv.maChucVu and nv.trangThai = 0";
        List<NhanVienModel> listNhanVien = query(sql, new NhanVienMapper());
        return listNhanVien.isEmpty() ? null : listNhanVien;
    }

}
