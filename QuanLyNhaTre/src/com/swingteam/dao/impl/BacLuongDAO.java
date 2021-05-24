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
        return query(sql, new BacLuongMapper());
    }

    @Override
    public int save(BacLuongModel bacLuongModel) {
        String sql = "insert into BacLuong (heSoBac,mucLuongCanBan) values (?,?)";
        return (int) insert(sql, bacLuongModel.getHeSoBac(),bacLuongModel.getMucLuongCanBan());
    }
    
}
