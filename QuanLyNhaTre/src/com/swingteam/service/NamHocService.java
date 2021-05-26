/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;

import com.swingteam.dao.NamHocDao;
import com.swingteam.model.NamhocModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NamHocService {
    NamHocDao dao;

    public NamHocService() {
        dao =new NamHocDao();
    }
    public List<NamhocModel> getNH(){
        return dao.getNH();
    }
     public void addNH(NamhocModel nh){
         dao.addNH(nh);
     }
}
