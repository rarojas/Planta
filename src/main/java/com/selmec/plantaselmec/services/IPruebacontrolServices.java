/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Pruebacontrol;
import com.selmec.plantaselmec.dto.PruebacontrolDTO;

/**
 *
 * @author rrojase
 */
public interface IPruebacontrolServices {

    void Save(Pruebacontrol prueba);

    void Update(Pruebacontrol prueba);

    Pruebacontrol Get(Integer id);

    PruebacontrolDTO DTO(Pruebacontrol prueba);
}
