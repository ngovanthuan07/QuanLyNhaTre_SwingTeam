/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service.impl;

import com.swingteam.dao.INhanVienDAO;
import com.swingteam.dao.impl.NhanVienDao;
import com.swingteam.model.NhanVienModel;
import com.swingteam.service.INhanVienService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class NhanVienService implements INhanVienService{
    
    public INhanVienDAO iNhanVienDAO;

    public NhanVienService() {
        iNhanVienDAO = new NhanVienDao();
    }
    
    
    
    
    @Override
    public List<NhanVienModel> findAll() {
        return iNhanVienDAO.findAll();
    }

    @Override
    public String save(NhanVienModel nhanVienModel) {
        return iNhanVienDAO.save(nhanVienModel);
    }

    @Override
    public void edit(NhanVienModel nhanVienModel, String maNhanVien) {
        iNhanVienDAO.edit(nhanVienModel, maNhanVien);
    }

    @Override
    public List<NhanVienModel> findAllKhongLam() {
        return iNhanVienDAO.findAllKhongLam();
    }

    @Override
    public List<NhanVienModel> findByCodeMaNhanVien(String maChucVuc) {
        return iNhanVienDAO.findByCodeMaNhanVien(maChucVuc);
    }

    @Override
    public List<NhanVienModel> findByCodeMaBacLuong(String maBacLuong) {
        return iNhanVienDAO.findByCodeMaBacLuong(maBacLuong);
    }
    
}
