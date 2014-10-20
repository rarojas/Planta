package com.selmec.plantaselmec.Dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 * @param <T>
 * @param <KEY>
 */
public abstract class BaseDAO< T extends Serializable, KEY extends Serializable> {

    private Class< T> clazz;

    @Autowired
    SessionFactory sessionFactory;

    public final void setClazz(Class< T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Transactional(readOnly = true)
    public T findOne(KEY id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @Transactional(readOnly = true)
    public List< T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Transactional
    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    @Transactional
    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    @Transactional
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

     @Transactional
    public void deleteById(KEY entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    public final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
