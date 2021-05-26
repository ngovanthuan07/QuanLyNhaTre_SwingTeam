/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.dao;

import com.swingteam.model.GiaoVienModel;
import java.util.List;

/**
 *
 * @author hungtr
 */
public interface IGiaoVienDAO {
   List<GiaoVienModel> findAll();  
   String save(GiaoVienModel giaoVienModel);
   List<GiaoVienModel> findAllBacLuong_GiaoVien();
   void edit(GiaoVienModel giaoVienModel,String maGiaoVien,int HeSo);
   boolean findByCMND(String cmnd);
   boolean findBySDT(String sdt);
}
