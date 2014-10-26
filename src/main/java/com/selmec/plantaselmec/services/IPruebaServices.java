/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IPruebaServices {//extends IBaseServices<Prueba, Integer> {

    List<Prueba> GetByUser(Usuarios usuarios);

    String CarrilByPrueba(int id);
    
    List<Prueba> GetAll();

    Prueba GetById(Integer id);//Este método me párace que no se usa en ningún mommento

    void Save(Prueba prueba);

    void Update(Prueba prueba);

    void Delete(Integer id);
    
    void cambioEstatusPrueba(int id, String nombreUsuario, EstadoPrueba estatus);
}
