/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.model;

/**
 *
 * @author ngova
 */
public class BacLuongModel {
    private String maBacLuong;
    private int heSoBac;
    private long mucLuongCanBan;
    private long tongLuong;

    public long getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(long tongLuong) {
        this.tongLuong = tongLuong;
    }
    
    
    public String getMaBacLuong() {
        return maBacLuong;
    }

    public void setMaBacLuong(String maBacLuong) {
        this.maBacLuong = maBacLuong;
    }

    public int getHeSoBac() {
        return heSoBac;
    }

    public void setHeSoBac(int heSoBac) {
        this.heSoBac = heSoBac;
    }

    public long getMucLuongCanBan() {
        return mucLuongCanBan;
    }

    public void setMucLuongCanBan(long mucLuongCanBan) {
        this.mucLuongCanBan = mucLuongCanBan;
    }
}
