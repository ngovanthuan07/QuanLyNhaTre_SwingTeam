/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swingteam.service;

import com.swingteam.model.BacLuongModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface IBacLuongService {
    List<BacLuongModel> finAll(); 
    String save(BacLuongModel bacLuongModel);    
}
