/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Prueba;

/**
 *
 * @author rrojase
 */
public class Pruebacontrol {

    public Pruebacontrol() {
    }

    public Pruebacontrol(Pruebacontrol prueba) {
        
    }

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
}
