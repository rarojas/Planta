/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.Dao;

import java.io.Serializable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rrojase
 * @param <T>
 * @param <KEY>
 */
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDao< T extends Serializable, KEY extends Serializable>
        extends BaseDAO< T, KEY> implements IGenericDao< T, KEY> {

}
