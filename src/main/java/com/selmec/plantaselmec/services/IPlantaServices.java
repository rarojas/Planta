/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.PlantaDTO;
import com.selmec.utils.services.IBaseServices;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IPlantaServices extends IBaseServices<Planta, String> {

    LecturaPSC LecturaPlanta(String Equipo);

    List<PlantaDTO> GetPlantas();

    List<PlantaDTO> GetPlantaByOP(String noOP);
}
