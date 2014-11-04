/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto.mapper;

import com.selmec.plantaselmec.Models.Ensamblearranque;
import com.selmec.plantaselmec.dto.EnsamblearranqueDTO;
import com.selmec.plantaselmec.dto.PruebaArranqueDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrojase
 */
@Component
public class EnsamblearranqueDTOMapper extends CustomMapper<Ensamblearranque, EnsamblearranqueDTO> {

    @Override
    public void mapAtoB(Ensamblearranque source, EnsamblearranqueDTO destination, MappingContext context) {
        destination.id = source.getId();
        destination.dtCreacion = source.getDtCreacion();
        destination.folio = source.getFolio();
        destination.pruebaarranques = mapperFacade.mapAsList(source.getPruebaarranques(), PruebaArranqueDTO.class);

    }
}
