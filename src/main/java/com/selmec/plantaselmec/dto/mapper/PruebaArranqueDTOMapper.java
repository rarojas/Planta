/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto.mapper;

import com.selmec.plantaselmec.Models.Pruebaarranque;
import com.selmec.plantaselmec.dto.PruebaArranqueDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrojase
 */
@Component
public class PruebaArranqueDTOMapper extends CustomMapper<Pruebaarranque, PruebaArranqueDTO> {

    @Override
    public void mapAtoB(Pruebaarranque a, PruebaArranqueDTO b, MappingContext context) {
        b.comentario = a.getComentario();
        b.dtFin = a.getDtFin();
        b.dtInicio = a.getDtInicio();
        b.id = a.getId();
        b.tipo = a.getTipo();
        b.estatus = a.getEstatus();        
    }
}
