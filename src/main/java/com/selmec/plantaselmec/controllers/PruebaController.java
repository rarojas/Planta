/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Lecturas;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/Pruebas")
public class PruebaController extends BaseController<Prueba, PruebaDTO> {

    @Transactional(readOnly = true)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<PruebaDTO> Get() {
        return Get(Prueba.class, PruebaDTO.class);
    }

    @Transactional(readOnly = true)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PruebaDTO Get(@PathVariable("id") int id) {
        
        return Get(id, Prueba.class, PruebaDTO.class);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PruebaDTO Post(@RequestBody Prueba prueba) {
        sessionFactory.getCurrentSession().save(prueba);
        return DTO(prueba, Prueba.class, PruebaDTO.class);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public PruebaDTO Put(@RequestBody Prueba prueba) {
        sessionFactory.getCurrentSession().update(prueba);
        return DTO(prueba, Prueba.class, PruebaDTO.class);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "Lecturas/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<LecturaDTO> Lecturas(@PathVariable("id") int id) {
        String hql = "from Lecturas l where l.prueba.id = " + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Lecturas> results = query.list();
        List<LecturaDTO> r = new ArrayList<>();
        for (Lecturas row : results) {
            r.add(new LecturaDTO(row));
        }
        return r;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable("id") int id) {
        Prueba prueba = (Prueba) sessionFactory.getCurrentSession().get(Prueba.class, id);
        sessionFactory.getCurrentSession().delete(prueba);
    }

}
