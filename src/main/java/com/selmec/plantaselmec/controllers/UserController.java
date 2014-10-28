/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import com.selmec.plantaselmec.services.IUsuariosServices;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
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
 * @author rrojase
 */
@Controller
@RequestMapping("api/Usuarios")
public class UserController extends BaseController<Usuarios, UsuarioDTO> {

    private IGenericDao<Usuarios, Integer> dao;

    @Autowired
    private IUsuariosServices usuariosServices;

    @Autowired
    public void setDao(IGenericDao< Usuarios, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Usuarios.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<UsuarioDTO> Get() {
        return DTO(usuariosServices.All(), Usuarios.class, UsuarioDTO.class);//
    }

    @RequestMapping(value = "/Current", method = RequestMethod.GET)
    @ResponseBody
    public String Current(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public UsuarioDTO Get(@PathVariable("id") int id) {
        return Get(id, Usuarios.class, UsuarioDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Usuarios Post(@Valid @RequestBody Usuarios usuarios) {//aquí no es como el get?, que retorna un tipo DTO
        usuariosServices.Save(usuarios);
        return usuarios;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void Put(@RequestBody Usuarios usuarios) {
        usuariosServices.Save(usuarios);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable("id") int id) {
        usuariosServices.Delete(id);
    }
    

}
