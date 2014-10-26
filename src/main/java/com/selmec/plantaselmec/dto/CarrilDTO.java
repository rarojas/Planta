/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Cariles;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rrojase
 */
//Changes faltaban algunos atributos 
public class CarrilDTO implements java.io.Serializable {

    public Integer id;
    public int noCarril;
    public String equipo;
    public String planta;
    //public Set ensambles = new HashSet(0);

    public CarrilDTO() {
    }

    public CarrilDTO(Cariles carril) {
        this.id = carril.getId();
        this.noCarril = carril.getNoCarril();
        this.equipo = carril.getEquipo();
        this.planta = carril.getPlanta();
        //this.ensambles=carril.getEnsambles();//Porque sea hace un DTO de Carriles,esque por lo que veo es que no habría problema de utilizar la entidad de carriles, sin embargo supongo que es por el patrón utilizado
    }

}