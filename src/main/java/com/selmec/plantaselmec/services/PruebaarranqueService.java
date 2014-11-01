/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Pruebaarranque;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrojase
 */
@Service
public class PruebaarranqueService extends BaseServices< Pruebaarranque, Integer> implements IPruebaarranqueService {

    @Autowired
    public void setDao(IGenericDao<Pruebaarranque, Integer> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Pruebaarranque.class);
    }

}
