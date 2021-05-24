/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service.impl;

import com.swingteam.dao.IChucVuDAO;
import com.swingteam.dao.impl.ChucVuDAO;
import com.swingteam.model.ChucVuModel;
import com.swingteam.service.IChucVuService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ChucVuService implements IChucVuService{

    public IChucVuDAO iChucVuDAO;
    
    public ChucVuService(){
        iChucVuDAO = new ChucVuDAO();
    }
    
    @Override
    public List<ChucVuModel> findAll() {
        return iChucVuDAO.finAll();
    }

    @Override
    public String save(ChucVuModel chucVuModel) {
        return iChucVuDAO.save(chucVuModel);
    }
    
}
