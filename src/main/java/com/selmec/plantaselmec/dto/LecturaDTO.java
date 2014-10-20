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

    public Date x;
    public Double val_0;
    public Double val_2;
    public Double val_1;

    public LecturaDTO() {
    }

    public LecturaDTO(Lecturas lectura) {
        this.val_1 = lectura.getL1l2();
        this.val_2 = lectura.getL2l3();
        this.val_0 = lectura.getL3l1();

        this.x = lectura.getTimeStamp();
    }
}
