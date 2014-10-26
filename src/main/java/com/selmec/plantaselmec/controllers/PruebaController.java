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
public class PruebaController extends BaseController<Prueba, PruebaDTO> {

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

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<PruebaDTO> Get() {
        return DTO(pruebaServices.GetAll(), Prueba.class, PruebaDTO.class);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PruebaDTO Get(@PathVariable("id") int id) {
        return Get(id, Prueba.class, PruebaDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PruebaDTO Post(@RequestBody Prueba prueba) {
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

    //debe llevar transactional read only
    @RequestMapping(value = "Autorizar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PruebaDTO AutorizarPruebaEjecutor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.AutorizadoEjecutor);
        return Get(id, Prueba.class, PruebaDTO.class);
    }

    @RequestMapping(value = "Autorizar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PruebaDTO RechazarPruebaEjecutor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.RechazadaEjecutor);
        return Get(id, Prueba.class, PruebaDTO.class);
    }

    @RequestMapping(value = "Autorizar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PruebaDTO AutorizarPruebasupervisor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.AutorizadaSupervisor);
        return Get(id, Prueba.class, PruebaDTO.class);
    }

    @RequestMapping(value = "Autorizar/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PruebaDTO RechazarPruebasupervisor(@PathVariable("id") int id, Principal principal) {
        pruebaServices.cambioEstatusPrueba(id, principal.getName(), EstadoPrueba.RechazadaSupervisor);
        return Get(id, Prueba.class, PruebaDTO.class);
    }
}
