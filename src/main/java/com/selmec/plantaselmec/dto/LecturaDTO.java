/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Lecturas;
import java.util.Date;

/**
 *
 * @author rrojase
 */
public class LecturaDTO {

    public Double L1L2;

    public Integer x;
    public Double L1N;
    public Double L2N;
    public Double L3N;
    public Integer Temp;
    public Double HZ;

    public LecturaDTO() {
    }

    public LecturaDTO(Lecturas lectura) {
        this.L1N = lectura.getL1();
        this.L2N = lectura.getL2();
        this.L3N = lectura.getL3();
        this.Temp = lectura.getTemp();
        this.HZ = lectura.getHz();
        this.x = lectura.getSegundo();

        //this.x = lectura.getTimeStamp();
    }
}
