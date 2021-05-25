/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;
import com.swingteam.dao.PhuHuynhDao;
import com.swingteam.model.PhuHuynhModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class PhuHuynhService {
    PhuHuynhDao phuHuynhDao;

    public PhuHuynhService() {
        phuHuynhDao = new PhuHuynhDao();
    }
    public List<PhuHuynhModel> getPH(){
        return phuHuynhDao.getPH();
    }
    public void addPH(PhuHuynhModel ph){
        phuHuynhDao.addPH(ph);
    }
    public void updatePH(PhuHuynhModel ph){
        phuHuynhDao.updatePH(ph);
    }
    public void deletePH(String maph){
        phuHuynhDao.deletePH(maph);
    }
    public PhuHuynhModel getPHByMa(String ma) throws IOException, SQLException{
        return phuHuynhDao.getPHByMa(ma);
    }
    public List<PhuHuynhModel> searchPHByMa(String maph) throws SQLException{
        return phuHuynhDao.searchPHByMa(maph);
    }
    public List<PhuHuynhModel> getPHCuaTre(String matre) throws SQLException{
        return phuHuynhDao.getPHCuaTre(matre);
    }
}
