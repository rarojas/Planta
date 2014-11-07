/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.Models;

import java.io.Serializable;

/**
 *
 * @author rrojase
 */
public class Rol implements Serializable{

    private Integer id;
    private String nbRol;

    /**
     * @return the ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param ID the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the rol
     */
    public String getNbRol() {
        return nbRol;
    }

    /**
     * @param rol the rol to set
     */
    public void setNbRol(String nbRol) {
        this.nbRol = nbRol;
    }

}
