/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;

import com.swingteam.dao.LopDao;
import com.swingteam.model.Lopmodel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LopService {
    LopDao dao;

    public LopService() {
        dao= new LopDao();
    }
    public List<Lopmodel> getLop(){
        return dao.getLop();
    }
     public void addLop(Lopmodel lop){
         dao.addLop(lop);
     }
}
