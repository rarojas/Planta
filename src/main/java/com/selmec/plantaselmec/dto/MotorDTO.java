package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Motores;

/**
 *
 * @author rrojase
 */
public class MotorDTO  {
    public int kwContinuo;
    public int kvaContinuo;
    public int ampContinuo;

    public MotorDTO() {
    }

    public MotorDTO(Motores motor) {
        this.modelo = motor.getModelo();
        this.frecuenciaOperacion = motor.getFrecuenciaOperacion();
        this.amp = motor.getAmp();
        this.kva = motor.getKva();
        this.kw = motor.getKw();
        this.ampContinuo = motor.getAmpContinuo();
        this.kvaContinuo = motor.getKvaContinuo();
        this.kwContinuo = motor.getKwContinuo();
        this.noFases = motor.getNoFases();
        this.rpm = motor.getRpm();
        this.marca = motor.getMarca();
        this.combustible = motor.getCombustible();
        this.generadorMarca = motor.getGeneradorMarca();
        this.generadorModelo = motor.getGeneradorModelo();
        this.derrateo = motor.getDerrateo();
        this.tipoControl = motor.getTipoControl();        

    }

    public String modelo;    
    public int frecuenciaOperacion;
    public int rpm;
    public int kw;
    public int kva;
    public int amp;
    public int noFases;
    public String marca;
    public int combustible;
    public String generadorMarca;
    public String generadorModelo;
    public float derrateo;
    public int tipoControl;
}
