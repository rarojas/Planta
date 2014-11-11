/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import java.util.Date;

/**
 *
 * @author rrojase
 */
public class EnsambleBaseDTO {

    public Integer id;
    public PlantaDTO planta;
    public UsuarioDTO usuarios;
    public String folio;
    public Date dtCreacion;
    public Date dtProgramada;
    public Date dtProgramadaReal;
}
