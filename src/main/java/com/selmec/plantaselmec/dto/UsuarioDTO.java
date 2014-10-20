/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Usuarios;

/**
 *
 * @author cognos
 */
public class UsuarioDTO {

    public String email;
    public String nombres;

    public UsuarioDTO(Usuarios usuarios) {
        this.nombres = usuarios.getNombres();
        this.email = usuarios.getEmail();
    }

}
