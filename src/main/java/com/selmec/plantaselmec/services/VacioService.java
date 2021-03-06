/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Vacio;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrojase
 */
@Service
public class VacioService extends BaseServices<Vacio, Integer> implements IVacioService {

    @Autowired
    public void setDao(IGenericDao<Vacio, Integer> dao) {
        this.dao = dao;
        dao.setClazz(Vacio.class);
    }
}
