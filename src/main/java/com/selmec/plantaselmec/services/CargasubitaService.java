/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Cargasubita;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GEIDAR
 */
@Service
public class CargasubitaService implements ICargasubitaService {

    private IGenericDao<Cargasubita, String> dao;//Como determinar de que tipo es el segunto parámetro, ya que en algunos veo que es de tipo String y en otros de tipo Integer

    @Autowired
    public void setDao(IGenericDao< Cargasubita, String> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Cargasubita.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cargasubita> GetAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cargasubita GetById(String id) {
        return dao.findOne(id);
    }

    @Transactional
    @Override
    public void Save(Cargasubita cargaSubita) {
        dao.create(cargaSubita);
    }

    @Transactional
    @Override
    public void Update(Cargasubita cargaSubita) {
        dao.update(cargaSubita);
    }

    @Transactional
    @Override
    public void Delete(String id) {
        dao.deleteById(id);
    }
}
