/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import com.selmec.plantaselmec.services.IUsuariosServices;
import java.security.Principal;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
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
   

    @Autowired
    private IUsuariosServices usuariosServices;

    @RequestMapping(value = "/Current", method = RequestMethod.GET)
    @ResponseBody
    public String Current(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UsuarioDTO> Get() {
        return usuariosServices.ToDTO(usuariosServices.All());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UsuarioDTO Get(@PathVariable("id") int id) {
        return usuariosServices.ToDTO(usuariosServices.GetUsuario(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UsuarioDTO Post(@RequestBody Usuarios usuario) {
        usuariosServices.Save(usuario);
        return usuariosServices.ToDTO(usuario);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void Put(@RequestBody Usuarios usuario) {
        usuariosServices.Save(usuario);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable("id") int id) {

    }

}
