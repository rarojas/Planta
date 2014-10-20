/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.Dao;

import com.selmec.plantaselmec.Models.Incidencias;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author rrojase
 * @param <T>
 * @param <KEY>
 */
public interface IGenericDao<T extends Serializable, KEY extends Serializable> {

    T findOne(final KEY id);

    List<T> findAll();

    void create(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final KEY entityId);

    void setClazz(Class<T> aClass);

    Session getCurrentSession();
}
