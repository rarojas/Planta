/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IPruebaServices extends
        IBaseServices<Prueba, Integer> {

    List<Prueba> GetByUser(Usuarios usuarios);
}
