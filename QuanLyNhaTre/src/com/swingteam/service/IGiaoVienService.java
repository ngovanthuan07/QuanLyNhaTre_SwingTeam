/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;

import com.swingteam.model.GiaoVienModel;
import java.util.List;

/**
 *
 * @author hungtr
 */
public interface IGiaoVienService {

    List<GiaoVienModel> findAll();

    String save(GiaoVienModel giaoVienModel);

    boolean findByCMND(String cmnd);

    boolean findBySDT(String sdt);
    
    void edit(GiaoVienModel giaoVienModel,String maGiaoVien,int HeSo);
    
    List<GiaoVienModel> findAllBacLuong_GiaoVien();
}
