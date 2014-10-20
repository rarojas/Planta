/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Cariles;

/**
 *
 * @author rrojase
 */
public class CarrilDTO {

    public Integer id;
    public int noCarril;

    public CarrilDTO() {
    }

    public CarrilDTO(Cariles carril) {
        this.id = carril.getId();
        this.noCarril = carril.getNoCarril();
    }
}
