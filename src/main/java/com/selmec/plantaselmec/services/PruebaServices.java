/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Cariles;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class PruebaServices extends BaseServices<Prueba, Integer> implements IPruebaServices {

    @Autowired
    public void setDao(IGenericDao< Prueba, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Prueba.class);
    }

    @Transactional
    @Override
    public List<Prueba> GetByUser(Usuarios usuarios) {
        return dao.getCurrentSession().createCriteria(Prueba.class).add(Restrictions.eq("ensamble.usuarioid.id", usuarios.getId())).list();
    }

    @Transactional
    @Override
    public String CarrilByPrueba(int id) {
        return dao.findOne(id).getEnsamble().getCariles().getEquipo();
    }
}
