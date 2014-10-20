/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.selmec.plantaselmec.Models.Incidencias;
import com.selmec.plantaselmec.Models.Prueba;
import java.util.Date;

public class PruebaDTO {

    public int id;
    public EnsambleDTO ensamble;
    public int tipo;
    public int estatus;
    @JsonDeserialize(using = DateDeserializer.class)
    public Date dtInicio;
    @JsonDeserialize(using = DateDeserializer.class)
    public Date dtFin;

    public String comentario;
    public IncidenciasDTO incidencias;

    public PruebaDTO() {
    }

    public PruebaDTO(Prueba prueba) {
        this.id = prueba.getId();
        this.dtInicio = prueba.getDtInicio();
        this.dtFin = prueba.getDtFin();
        this.estatus = prueba.getEstatus();
        this.tipo = prueba.getTipo();
        this.comentario = prueba.getComentario();
        if (prueba.getIncidencias() != null) {
            this.incidencias = new IncidenciasDTO(prueba.getIncidencias());
        }
    }
}
