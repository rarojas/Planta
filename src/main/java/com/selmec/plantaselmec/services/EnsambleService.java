/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.EstadoEnsamble;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class EnsambleService extends BaseServices<Ensamble, Integer> implements IEnsambleService {

    private final Logger logger = Logger.getLogger(EnsambleService.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    public void setDao(IGenericDao<Ensamble, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Ensamble.class);
    }

    @Transactional
    @Override
    public List<Ensamble> GetByUser(Usuarios usuarios) {
        return dao.getCurrentSession().createCriteria(Ensamble.class).add(Restrictions.eq("usuarios.id", usuarios.getId())).list();
    }

    @Override
    public void ExcuteSPControl(int estado) {
        String queryString;
        queryString = String.format("EXECUTE PROCEDURE sp_control(%s,'CTE0000003','UBC0000004','EQ00000081')", estado);
        logger.info(estado);
        logger.info(queryString);
        this.jdbctemplate = new JdbcTemplate(dataSource);
        jdbctemplate.update(queryString);
        logger.info("EXECUTE PROCEDURE sp_control(%s,'CTE0000003','UBC0000004,'EQ00000081') execute successfully");
    }

    @Override
    public void TurnOnCarril(int estado) {
        String queryString;
        Ensamble prueba = (Ensamble) dao.getCurrentSession().get(Ensamble.class, estado);
        String equipo = prueba.getCariles().getPlanta();
        queryString = String.format(
                "update  tablaescritura  set  tagvalue = 510 where  variable = 'CONTROL1' AND IDEQUIPO = '%s';"
                + "update tablaescritura set tagvalue = 1 where variable = 'MODO CONTROL' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 0 where  variable = 'CONTROL2' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 1 where  variable = 'CONTROL3' AND IDEQUIPO = '%s';", equipo, equipo, equipo, equipo);
        logger.info(estado);
        logger.info(queryString);
        this.jdbctemplate = new JdbcTemplate(dataSource);
        jdbctemplate.update(queryString);
        logger.info("Record inserted successfully");
    }

    @Override
    public void TurnOffCarril(int estado) {
        String queryString;
        Ensamble prueba = (Ensamble) dao.getCurrentSession().get(Ensamble.class, estado);
        String equipo = prueba.getCariles().getPlanta();
        queryString = String.format(
                "update tablaescritura set tagvalue = 1 where variable = 'MODO CONTROL' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 765 where  variable = 'CONTROL1' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 0 where  variable = 'CONTROL2' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 1 where  variable = 'CONTROL3' AND IDEQUIPO = '%s';", equipo, equipo, equipo, equipo);
        logger.info(estado);
        logger.info(queryString);
        this.jdbctemplate = new JdbcTemplate(dataSource);
        jdbctemplate.update(queryString);
        logger.info("Record inserted successfully");
    }

    @Transactional
    @Override
    public void Rechazar(int id, Usuarios usuario) {
        Ensamble ensamble = dao.findOne(id);
        ensamble.setEstatus(EstadoEnsamble.Rechazada);
        ensamble.setDtAutorizacion(new Date());
    }

    @Transactional
    @Override
    public void Aprobar(int id, Usuarios usuario) {
        Ensamble ensamble = dao.findOne(id);
        ensamble.setDtAutorizacion(new Date());
        ensamble.setEstatus(EstadoEnsamble.Aprobada);
    }
}
