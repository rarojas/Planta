/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Cargasubita;
import java.util.List;

/**
 *
 * @author GEIDAR
 */
public interface ICargasubitaService {
     List<Cargasubita> GetAll();

    Cargasubita GetById(Integer id);

    void Save(Cargasubita cargaSubita);

    void Update(Cargasubita cargaSubita);

    void Delete(Integer id);
}
