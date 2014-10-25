/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Pruebacontrol;
import com.selmec.plantaselmec.dto.PruebacontrolDTO;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class PruebacontrolServices implements IPruebacontrolServices {

    private IGenericDao<Pruebacontrol, Integer> dao;

    @Autowired
    public void setDao(IGenericDao< Pruebacontrol, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Pruebacontrol.class);
    }

    @Transactional
    @Override
    public void Save(Pruebacontrol prueba) {
        dao.create(prueba);
    }

    @Transactional(readOnly = true)
    @Override
    public Pruebacontrol Get(Integer id) {
        return dao.findOne(id);
    }

    @Transactional
    @Override
    public void Update(Pruebacontrol prueba) {
        dao.update(prueba);
    }

    public PruebacontrolDTO DTO(Pruebacontrol prueba) {
        return mapper.map(prueba, PruebacontrolDTO.class);
    }
    
    @Autowired
    private MapperFacade mapper;
}
