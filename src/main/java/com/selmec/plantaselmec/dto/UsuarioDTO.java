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
public class UsuarioDTO implements java.io.Serializable {

    public String email;
    public String nombres;
    public String apellidos;
    public String rol;
    public int id;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuarios usuarios) {
        this.nombres = usuarios.getNombres();
        this.email = usuarios.getEmail();
        this.apellidos = usuarios.getApellidos();
        this.rol = usuarios.getRol();
        this.id = usuarios.getId();
    }

}
