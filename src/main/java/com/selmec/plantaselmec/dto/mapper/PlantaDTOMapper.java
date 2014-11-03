/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto.mapper;

import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.dto.MotorDTO;
import com.selmec.plantaselmec.dto.PlantaDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

/**
 *
 * @author rrojase
 */
public class PlantaDTOMapper extends CustomMapper<Planta, PlantaDTO> {

    @Override
    public void mapAtoB(Planta source, PlantaDTO destination, MappingContext context) {
        destination = this.mapperFacade.map(source, PlantaDTO.class);
        destination.motores = this.mapperFacade.map(source.getMotores(), MotorDTO.class);
    }
}
