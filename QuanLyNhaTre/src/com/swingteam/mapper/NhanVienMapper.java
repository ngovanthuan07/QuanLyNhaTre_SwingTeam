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
public class NhanVienMapper implements RowMapper<NhanVienModel>{
private String maNhanVien;
    private String tenNhanVien;
    private String ngaySinh;
    private byte gioiTinh;
    private String cmnd;
    private String ngayVaoLam;
    private String diaChi;
    private String soDienThoai;
    private byte trangThai;
    private String ghiChu;
    private String maBacLuong;
    private int heSoBac;
    private String maChucVu;
    private String tenChucVu;
    @Override
    public NhanVienModel mapRow(ResultSet rs) {
        try {
            NhanVienModel nv = new NhanVienModel();
            nv.setMaNhanVien(rs.getString("maNhanVien"));
            nv.setTenNhanVien(rs.getString("tenNhanVien"));
            nv.setNgaySinh(rs.getString("ngaySinh"));
            nv.setGioiTinh(rs.getByte(gioiTinh));
            nv.setCmnd(rs.getString("cmnd"));
            nv.setNgayVaoLam(rs.getString("ngayVaoLam"));
            nv.setDiaChi(rs.getString("diaChi"));
            nv.setSoDienThoai(rs.getString("soDienThoai"));
            nv.setTrangThai(rs.getByte("trangThai"));
            nv.setGhiChu(rs.getString("ghiChu"));
            nv.setMaBacLuong(rs.getString("maBacLuong"));
            nv.setHeSoBac(rs.getInt("heSoBac"));
            nv.setMaChucVu(rs.getString("maChucVu"));
            nv.setTenChucVu(rs.getString("tenChucVu"));
            return nv;
        } catch (Exception e) {
            return null;
        }
    }
    
}
