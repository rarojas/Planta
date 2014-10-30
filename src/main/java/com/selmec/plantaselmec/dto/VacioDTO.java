/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

/**
 *
 * @author GEIDAR
 */
public class VacioDTO implements java.io.Serializable {

    public int id;
    //public PruebaDTO prueba;
    public boolean proteccion;
    public boolean presion;
    public boolean temperatura;
    public boolean sobrevelocidad;
    public boolean ajustevoltaje;
    public boolean ajustehz;
    public boolean fugas;
    public boolean fugaescape;
    public boolean fases;

    public VacioDTO() {
    }

}
