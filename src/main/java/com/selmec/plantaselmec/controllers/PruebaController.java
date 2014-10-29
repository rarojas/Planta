/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Lecturas;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import com.selmec.plantaselmec.services.IPruebaServices;
import com.selmec.plantaselmec.services.IUsuariosServices;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author GEID@R
 */
@Controller
@RequestMapping("api/Pruebas")
public class PruebaController extends BaseControllers<Prueba, PruebaDTO> {

    private final Logger logger = Logger.getLogger(PruebaController.class);
    @Autowired
    IUsuariosServices usuariosServices;
    @Autowired
    IPruebaServices pruebaServices;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<PruebaDTO> Get(Principal principal) {
        Usuarios usuarios = usuariosServices.GetByUsername(principal.getName());
        List<Prueba> pruebas = pruebaServices.GetByUser(usuarios);
        return this.DTO(pruebas, Prueba.class, PruebaDTO.class);
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PruebaDTO Get(@PathVariable("id") int id) {
        return Get(id, Prueba.class, PruebaDTO.class);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PruebaDTO Post(@Valid @RequestBody Prueba prueba) {
        logger.info(prueba.getId());
        logger.info(prueba.getEnsamble());
        pruebaServices.Save(prueba);
        return DTO(prueba, Prueba.class, PruebaDTO.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public PruebaDTO Put(@Valid @RequestBody Prueba prueba) {
        pruebaServices.Update(prueba);
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable Integer id) {
        pruebaServices.Delete(id);
    }

    @RequestMapping(value = "AutorizarE/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void AutorizarPruebaEjecutor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.AutorizadoEjecutor);
    }

    @RequestMapping(value = "RechazarE/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void RechazarPruebaEjecutor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.RechazadaEjecutor);

    }

    @RequestMapping(value = "AutorizarS/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void AutorizarPruebasupervisor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.AutorizadaSupervisor);

    }

    @RequestMapping(value = "RechazarS/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void RechazarPruebasupervisor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.RechazadaSupervisor);
    }
}
