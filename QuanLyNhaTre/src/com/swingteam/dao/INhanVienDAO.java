/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;

import com.swingteam.model.NhanVienModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface INhanVienDAO {
    List<NhanVienModel> findAll();
    String save(NhanVienModel nhanVienModel);
    void edit(NhanVienModel nhanVienModel,String maNhanVien);
    List<NhanVienModel> findAllKhongLam();
    List<NhanVienModel> findByCodeMaNhanVien(String maChucVuc);
    List<NhanVienModel> findByCodeMaBacLuong(String maBacLuong);
}
