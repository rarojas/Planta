/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Prueba;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rrojase
 */
public class PruebaServices {
    
    IGenericDao<Prueba, Integer> dao;
    
    @Autowired
    public void setDao(IGenericDao<Prueba, Integer> dao) {
        this.dao = dao;
        dao.setClazz(Prueba.class);
    }
    
}
