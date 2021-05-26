/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;

import com.swingteam.dao.TreHocLopDao;
import com.swingteam.model.TreHocLopModel;
import com.swingteam.model.TreModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TreHocLopService {
    TreHocLopDao dao;

    public TreHocLopService() {
        dao=new TreHocLopDao();
    }
    public List<TreHocLopModel> getTreHocLop(){
        return dao.getTreHocLop();
    }
    public void addTreHocLop(TreHocLopModel tre){
        dao.addTreHocLop(tre);
    }
     public void updateTreHocLop(TreHocLopModel tre){
         dao.updateTreHocLop(tre);
     }
      public void deleteTreHocLop(String matre){
          dao.deleteTreHocLop(matre);
      }
       public TreHocLopModel getTreByMa(String ma) throws IOException, SQLException{
           return dao.getTreByMa(ma);
       }
       public List<TreModel> searchtre(String malop) throws SQLException{
           return dao.searchtre(malop);
       }
}
