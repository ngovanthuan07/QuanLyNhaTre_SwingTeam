/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;

import com.swingteam.dao.HocPhiDao;
import com.swingteam.model.HocPhiModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HocPhiService {
    HocPhiDao hocPhiDao;
    
    public HocPhiService(){
        hocPhiDao= new HocPhiDao();
    }
    public List<HocPhiModel> getHocPHi(){
        return hocPhiDao.getHocPHi();
    }
    public void addHocPHi(HocPhiModel hPhi){
        hocPhiDao.addHocPHi(hPhi);
    }
    public void updateHocPhi(HocPhiModel hPhi){
        hocPhiDao.updateHocPhi(hPhi);
    }
    public void deleteHocPhi(String maHP){
        hocPhiDao.deleteHocPhi(maHP);
    }
    public HocPhiModel getHocPhiByMa(String maHP) throws IOException, SQLException{
        return hocPhiDao.getHocPhiByMa(maHP);
    }
}
