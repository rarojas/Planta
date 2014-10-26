/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GEID@R
 */
@Service
public class PruebaServices implements IPruebaServices {

    private IGenericDao<Prueba, Integer> dao;

    @Autowired
    IUsuariosServices usuariosServices;

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

    @Transactional(readOnly = true)
    @Override
    public List<Prueba> GetAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Prueba GetById(Integer id) {
        return dao.findOne(id);
    }

    @Transactional
    @Override
    public void Save(Prueba prueba) {
        dao.create(prueba);
    }

    @Transactional
    @Override
    public void Update(Prueba prueba) {
        dao.update(prueba);
    }

    @Transactional
    @Override
    public void Delete(Integer id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void cambioEstatusPrueba(int id, String nombreUsuario, EstadoPrueba estatus) {

        Prueba prueba = (Prueba) dao.getCurrentSession().get(Prueba.class, id);
        Usuarios usuario = usuariosServices.GetByUsername(nombreUsuario);
        prueba.setAprueba(usuario.getId());
        Date today = Calendar.getInstance().getTime();
        prueba.setDtAprueba(today);
        prueba.setEstatus((Integer) estatus.ordinal());
        dao.getCurrentSession().merge(prueba);
    }

}
