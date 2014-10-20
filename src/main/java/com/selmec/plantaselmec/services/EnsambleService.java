/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.Prueba;
import javax.sql.DataSource;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

/**
 *
 * @author rrojase
 */
@Service
public class EnsambleService implements IEnsambleService {

    private IGenericDao<Ensamble, String> dao;

    private final Logger logger = Logger.getLogger(EnsambleService.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    public void setDao(IGenericDao<Ensamble, String> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Ensamble.class);
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
                + "update tablaescritura set tagvalue = 1 where variable = 'MODOCONTROL' AND IDEQUIPO = '%s';"
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
                "update tablaescritura set tagvalue = 1 where variable = 'MODOCONTROL' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 765 where  variable = 'CONTROL1' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 0 where  variable = 'CONTROL2' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 1 where  variable = 'CONTROL3' AND IDEQUIPO = '%s';", equipo, equipo, equipo, equipo);
        logger.info(estado);
        logger.info(queryString);
        this.jdbctemplate = new JdbcTemplate(dataSource);
        jdbctemplate.update(queryString);
        logger.info("Record inserted successfully");
    }
}
