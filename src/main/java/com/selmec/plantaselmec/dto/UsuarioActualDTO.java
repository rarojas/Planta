/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Rol;
import com.selmec.plantaselmec.Models.Usuarios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GEIDAR
 */
public class UsuarioActualDTO {

    public String username;
    public List<String> roles = new ArrayList<>();
    public String email;

    public UsuarioActualDTO() {
    }

    public UsuarioActualDTO(Usuarios usuario) {
        this.username = usuario.getUsername();
        for (Object rol : usuario.getRoles()) {
            this.roles.add(((Rol) rol).getNbRol());
        }
        this.email = usuario.getEmail();
    }
}
