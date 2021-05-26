/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service.impl;

import com.swingteam.dao.IGiaoVienDAO;
import com.swingteam.dao.impl.GiaoVienDao;
import com.swingteam.model.GiaoVienModel;
import com.swingteam.service.IGiaoVienService;
import java.util.List;

/**
 *
 * @author hungtr
 */
public class GiaoVienService implements IGiaoVienService{
    
    public IGiaoVienDAO iGiaoVienDAO;

    public GiaoVienService() {
        iGiaoVienDAO = new GiaoVienDao();
    }
    
    
    
    
    @Override
    public List<GiaoVienModel> findAll() {
        return iGiaoVienDAO.findAll();
    }

    @Override
    public String save(GiaoVienModel giaoVienModel) {
        return iGiaoVienDAO.save(giaoVienModel);
    }

    @Override
    public boolean findByCMND(String cmnd) {
        return iGiaoVienDAO.findByCMND(cmnd);
    }

    @Override
    public boolean findBySDT(String sdt) {
        return iGiaoVienDAO.findBySDT(sdt);
    }

    @Override
    public void edit(GiaoVienModel giaoVienModel, String maNhanVien, int HeSo) {
        iGiaoVienDAO.edit(giaoVienModel, maNhanVien,HeSo);
    }


    @Override
    public List<GiaoVienModel> findAllBacLuong_GiaoVien() {
       return iGiaoVienDAO.findAllBacLuong_GiaoVien();
    }
    
}
