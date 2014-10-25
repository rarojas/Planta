/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IBaseServices<T,K> {
     
    List<T> Get();
   
    T Get(K id);
    
    void Save(T entity);
        
    void Update(T entity);
        
    void Delete(K id);    
    
}
