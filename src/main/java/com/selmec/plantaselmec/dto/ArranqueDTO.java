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
public class ArranqueDTO extends PruebaArranqueDTO implements java.io.Serializable {

    public int id;
    public boolean instrumentos;
    public boolean regulador;
    public boolean maestro;
    public boolean multimetro;
    public boolean amperimetro;
    public boolean frecuencimetro;
    public boolean horometro;
    public boolean selector;
    public Boolean fusibles;

}
