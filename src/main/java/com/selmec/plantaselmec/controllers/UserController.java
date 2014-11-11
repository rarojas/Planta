/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.TokenTransfer;
import com.selmec.plantaselmec.dto.UsuarioActualDTO;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import com.selmec.plantaselmec.services.IUsuariosServices;
import com.selmec.rest.TokenUtils;
import com.selmec.utils.dao.IGenericDao;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/Usuarios")
public class UserController extends BaseControllers<Usuarios, UsuarioDTO> {

    private IGenericDao<Usuarios, Integer> dao;

    @Autowired
    private IUsuariosServices usuariosServices;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userService;

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
    Usuarios Post(@Valid @RequestBody Usuarios usuarios) {
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

    @RequestMapping(value = "getDataUser", method = RequestMethod.GET)
    @ResponseBody
    public UsuarioActualDTO getDataUser(Principal principal) {
        return usuariosServices.getDataUser(principal.getName());
    }

    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    @ResponseBody
    public TokenTransfer authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /*
         * Reload user as password of authentication principal will be null after authorization and
         * password is needed for token generation
         */
        UserDetails userDetails = this.userService.loadUserByUsername(username);

        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }

}
