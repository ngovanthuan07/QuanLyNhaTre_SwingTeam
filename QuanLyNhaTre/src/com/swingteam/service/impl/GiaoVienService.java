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
    
}
