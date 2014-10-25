/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Pruebacontrol;

/**
 *
 * @author rrojase
 */
//Changes ALL
public class PruebaControlDTO implements java.io.Serializable {//Changes: implemntar Serializable

    public int id;
    public int termometro;
    public Integer presion;
    public Integer saquemarcha;
    public String sobrevelocidad;
    public String ubt;
    public Boolean operacionubt;
    public Boolean cargaliena;
    public Boolean altatemperatura;
    public Boolean bajapresion;
    public Boolean fallageneral;
    public Boolean arranquemanual;
    public Boolean bajepresion;
    public Boolean temperatura;
    public Boolean proteccionsobrevelocidad;
    public Integer intentosarranque;
    public Integer duraciontotal;

    //Changes
    public String pruebacontrolcol;
    //public PruebaDTO prueba;

    public PruebaControlDTO() {
    }

    public PruebaControlDTO(Pruebacontrol pruebaControl) {
        this.presion = pruebaControl.getPresion();
        this.saquemarcha = pruebaControl.getSaquemarcha();
        this.sobrevelocidad = pruebaControl.getSobrevelocidad();
        this.ubt = pruebaControl.getUbt();
        this.operacionubt = pruebaControl.getOperacionubt();
        this.cargaliena = pruebaControl.getCargaliena();
        this.altatemperatura = pruebaControl.getAltatemperatura();
        this.bajapresion = pruebaControl.getBajapresion();
        this.fallageneral = pruebaControl.getFallageneral();
        this.arranquemanual = pruebaControl.getArranquemanual();
        this.bajepresion = pruebaControl.getBajepresion();
        this.temperatura = pruebaControl.getTemperatura();
        this.proteccionsobrevelocidad = pruebaControl.getProteccionsobrevelocidad();
        this.intentosarranque = pruebaControl.getIntentosarranque();
        this.duraciontotal = pruebaControl.getDuraciontotal();
        this.pruebacontrolcol = pruebaControl.getPruebacontrolcol();

        /*if (pruebaControl.getPrueba() != null) {
            this.prueba = new PruebaDTO(pruebaControl.getPrueba());
        }*/
    }
    //
}
