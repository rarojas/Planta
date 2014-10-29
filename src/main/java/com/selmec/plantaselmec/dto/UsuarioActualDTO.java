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

import com.selmec.plantaselmec.Models.Usuarios;

/**
 *
 * @author GEIDAR
 */
public class UsuarioActualDTO {

    public String username;
    public String rol;
    public String email;

    public UsuarioActualDTO() {
    }

    public UsuarioActualDTO(Usuarios usuario) {
        this.username = usuario.getUsername();
        this.rol = usuario.getRol();
        this.email = usuario.getEmail();
    }
}
