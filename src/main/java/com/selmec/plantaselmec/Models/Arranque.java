package com.selmec.plantaselmec.Models;
// Generated Oct 30, 2014 5:43:43 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Arranque generated by hbm2java
 */
@Entity
@Table(name = "arranque", catalog = "casetapruebas"
)
public class Arranque extends Pruebaarranque implements java.io.Serializable {

    private Pruebaarranque pruebaarranque;
    private boolean instrumentos;
    private boolean regulador;
    private boolean maestro;
    private boolean multimetro;
    private boolean amperimetro;
    private boolean frecuencimetro;
    private boolean horometro;
    private boolean selector;
    private Boolean fusibles;

    public Arranque() {
    }

    public Arranque(Pruebaarranque pruebaarranque, boolean instrumentos, boolean regulador, boolean maestro, boolean multimetro, boolean amperimetro, boolean frecuencimetro, boolean horometro, boolean selector) {
        this.pruebaarranque = pruebaarranque;
        this.instrumentos = instrumentos;
        this.regulador = regulador;
        this.maestro = maestro;
        this.multimetro = multimetro;
        this.amperimetro = amperimetro;
        this.frecuencimetro = frecuencimetro;
        this.horometro = horometro;
        this.selector = selector;
    }

    public Arranque(Pruebaarranque pruebaarranque, boolean instrumentos, boolean regulador, boolean maestro, boolean multimetro, boolean amperimetro, boolean frecuencimetro, boolean horometro, boolean selector, Boolean fusibles) {
        this.pruebaarranque = pruebaarranque;
        this.instrumentos = instrumentos;
        this.regulador = regulador;
        this.maestro = maestro;
        this.multimetro = multimetro;
        this.amperimetro = amperimetro;
        this.frecuencimetro = frecuencimetro;
        this.horometro = horometro;
        this.selector = selector;
        this.fusibles = fusibles;
    }

//    @OneToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    public Pruebaarranque getPruebaarranque() {
//        return this.pruebaarranque;
//    }
//
//    public void setPruebaarranque(Pruebaarranque pruebaarranque) {
//        this.pruebaarranque = pruebaarranque;
//    }

    @Column(name = "instrumentos", nullable = false)
    public boolean isInstrumentos() {
        return this.instrumentos;
    }

    public void setInstrumentos(boolean instrumentos) {
        this.instrumentos = instrumentos;
    }

    @Column(name = "regulador", nullable = false)
    public boolean isRegulador() {
        return this.regulador;
    }

    public void setRegulador(boolean regulador) {
        this.regulador = regulador;
    }

    @Column(name = "maestro", nullable = false)
    public boolean isMaestro() {
        return this.maestro;
    }

    public void setMaestro(boolean maestro) {
        this.maestro = maestro;
    }

    @Column(name = "multimetro", nullable = false)
    public boolean isMultimetro() {
        return this.multimetro;
    }

    public void setMultimetro(boolean multimetro) {
        this.multimetro = multimetro;
    }

    @Column(name = "amperimetro", nullable = false)
    public boolean isAmperimetro() {
        return this.amperimetro;
    }

    public void setAmperimetro(boolean amperimetro) {
        this.amperimetro = amperimetro;
    }

    @Column(name = "frecuencimetro", nullable = false)
    public boolean isFrecuencimetro() {
        return this.frecuencimetro;
    }

    public void setFrecuencimetro(boolean frecuencimetro) {
        this.frecuencimetro = frecuencimetro;
    }

    @Column(name = "horometro", nullable = false)
    public boolean isHorometro() {
        return this.horometro;
    }

    public void setHorometro(boolean horometro) {
        this.horometro = horometro;
    }

    @Column(name = "selector", nullable = false)
    public boolean isSelector() {
        return this.selector;
    }

    public void setSelector(boolean selector) {
        this.selector = selector;
    }

    @Column(name = "fusibles")
    public Boolean getFusibles() {
        return this.fusibles;
    }

    public void setFusibles(Boolean fusibles) {
        this.fusibles = fusibles;
    }

}
