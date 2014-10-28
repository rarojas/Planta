/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Cargasubita;

/**
 *
 * @author GEIDAR
 */
public class CargasubitaDTO implements java.io.Serializable {

    public int id;
    //
    public PruebaDTO prueba;
    public Integer seg;
    public Double vinicio;
    public Double vfinal;
    public Double hfinal;
    public Double hinicio;
    public Double icarga;

    public CargasubitaDTO() {
    }

    public CargasubitaDTO(Cargasubita cargaSubita) {
        this.id = cargaSubita.getId();
        this.seg = cargaSubita.getSeg();
        this.vinicio = cargaSubita.getVinicio();
        this.vfinal = cargaSubita.getVfinal();
        this.hfinal = cargaSubita.getHfinal();
        this.hinicio = cargaSubita.getHinicio();
        this.icarga = cargaSubita.getIcarga();
//        if (cargaSubita.getPrueba() != null) {
//            this.prueba = new PruebaDTO(cargaSubita.getPrueba());
//        }
    }
}
