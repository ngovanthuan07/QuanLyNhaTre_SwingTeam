/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service.impl;

import com.swingteam.dao.IBacLuongDAO;
import com.swingteam.dao.impl.BacLuongDAO;
import com.swingteam.model.BacLuongModel;
import com.swingteam.service.IBacLuongService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class BacLuongService implements IBacLuongService{
    
    public IBacLuongDAO iBacLuongDAO;
    
    public BacLuongService(){
        iBacLuongDAO = new BacLuongDAO();
    }

    @Override
    public List<BacLuongModel> finAll() {
        return iBacLuongDAO.finAll();
    }

    @Override
    public String save(BacLuongModel bacLuongModel) {
        return iBacLuongDAO.save(bacLuongModel);
    }

    @Override
    public void edit(BacLuongModel bacluong, String maBacLuong) {
        iBacLuongDAO.edit(bacluong, maBacLuong);
    }

    @Override
    public void delete(String maBacLuong) {
        iBacLuongDAO.delete(maBacLuong);
    }
    
}
