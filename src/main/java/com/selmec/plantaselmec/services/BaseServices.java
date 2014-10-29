/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.utils.dao.IGenericDao;
import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 * @param <T>
 * @param <K>
 */
public class BaseServices<T extends Serializable, K extends Serializable> implements IBaseServices<T, K> {

    public IGenericDao<T, K> dao;

    @Transactional
    @Override
    public List<T> Get() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public T Get(K id) {
        return dao.findOne(id);
    }

    @Transactional
    @Override
    public void Save(T entity) {
        dao.create(entity);
    }

    @Transactional
    @Override
    public void Update(T entity) {
        dao.update(entity);
    }

    @Transactional
    @Override
    public void Delete(K id) {
        dao.deleteById(id);
    }

}
