/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Cariles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class CarrilesService implements ICarrilesService {

    private IGenericDao<Cariles, Integer> dao;

    @Autowired
    public void setDao(IGenericDao< Cariles, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Cariles.class);
    }

    @Transactional
    @Override
    public List<Cariles> GetAll() {
        return dao.findAll();
    }

}
