/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Lecturas;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Pruebabase;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.utils.dao.IGenericDao;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class LecturasService implements ILecturasService {

    IGenericDao<Lecturas, Integer> dao;

    @Autowired
    public void setDao(IGenericDao<Lecturas, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Lecturas.class);
    }

    @Async
    @Transactional
    @Override
    public void Save(LecturaPSC result, int PruebaId) {
        Pruebabase prueba = new Pruebabase();//(Pruebabase) dao.getCurrentSession().get(Pruebabase.class, PruebaId);
        prueba.setId(PruebaId);
        Lecturas lectura = mapper.map(result, Lecturas.class);
        lectura.setPruebabase(prueba);
        dao.create(lectura);
    }

    @Autowired
    private MapperFacade mapper;

}
