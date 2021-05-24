/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao.impl;

import com.swingteam.dao.IChucVuDAO;
import com.swingteam.mapper.ChucVuMapper;
import com.swingteam.model.ChucVuModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ChucVuDAO extends AbstractDAO<ChucVuModel> implements IChucVuDAO{

    @Override
    public List<ChucVuModel> finAll() {
        String sql = "select * from ChucVu";
        List<ChucVuModel> listChucVu = query(sql, new ChucVuMapper());
        return listChucVu.isEmpty() ? null : listChucVu;
    }

    @Override
    public String save(ChucVuModel chucVuModel) {
        String sql = "insert into ChucVu(maChucVu,tenChucVu) values (?,?)";
        return (String) insert(sql, chucVuModel.getMaChucVu(),chucVuModel.getTenChucVu());
    }
    
}
