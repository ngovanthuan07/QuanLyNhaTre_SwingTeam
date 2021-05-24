/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;

import com.swingteam.dao.TreDao;
import com.swingteam.model.TreModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TreSerVice {
    TreDao treDao;

    public TreSerVice() {
        treDao= new TreDao();
    }
    public List<TreModel> getTre(){
        return treDao.getTre();
    }
    public void addTre(TreModel tre){
        treDao.addTre(tre);
    }
    public void updateTre(TreModel tre){
        treDao.updateTre(tre);
    }
    public void deleteTre(String matre){
        treDao.deleteTre(matre);
    }
    public TreModel getTreByMa(String ma) throws IOException, SQLException{
        return treDao.getTreByMa(ma);      
    }
    public List<TreModel> searchTreByTen(String tentre) throws SQLException{
        return treDao.searchTreByTen(tentre);
}
    public List<TreModel> searchTreByMatre(String matre) throws SQLException{
        return treDao.searchTreByMatre(matre);
    }
}
