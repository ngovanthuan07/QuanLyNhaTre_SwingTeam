/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao.impl;

import com.swingteam.dao.IBacLuongDAO;
import com.swingteam.mapper.BacLuongMapper;
import com.swingteam.model.BacLuongModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class BacLuongDAO extends AbstractDAO<BacLuongModel> implements IBacLuongDAO{

    @Override
    public List<BacLuongModel> finAll() {
        String sql = "select * from BacLuong";
        List<BacLuongModel> listBacLuong = query(sql, new BacLuongMapper());
        return listBacLuong.isEmpty() ? null : listBacLuong;
    }

    @Override
    public String save(BacLuongModel bacLuongModel) {
        String sql = "insert into BacLuong (maBacLuong,heSoBac,mucLuongCanBan) values (?,?,?)";
        return (String) insert(sql, bacLuongModel.getMaBacLuong(),bacLuongModel.getHeSoBac(),bacLuongModel.getMucLuongCanBan());
    }
    
}
