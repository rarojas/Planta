package com.selmec.plantaselmec.Models;
// Generated Oct 30, 2014 5:43:43 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Pruebacarga generated by hbm2java
 */
@Entity
@Table(name = "pruebacarga", catalog = "casetapruebas"
)
public class Pruebacarga extends Prueba implements java.io.Serializable {

    private Prueba prueba;
    private Double vmax;
    private Double vmin;
    private Double hmax;
    private Double hmin;

    public Pruebacarga() {
    }

    public Pruebacarga(Prueba prueba) {
        this.prueba = prueba;
    }

    public Pruebacarga(Prueba prueba, Double vmax, Double vmin, Double hmax, Double hmin) {
        this.prueba = prueba;
        this.vmax = vmax;
        this.vmin = vmin;
        this.hmax = hmax;
        this.hmin = hmin;
    }

//    @OneToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    public Prueba getPrueba() {
//        return this.prueba;
//    }
//
//    public void setPrueba(Prueba prueba) {
//        this.prueba = prueba;
//    }

    @Column(name = "vmax", precision = 22, scale = 0)
    public Double getVmax() {
        return this.vmax;
    }

    public void setVmax(Double vmax) {
        this.vmax = vmax;
    }

    @Column(name = "vmin", precision = 22, scale = 0)
    public Double getVmin() {
        return this.vmin;
    }

    public void setVmin(Double vmin) {
        this.vmin = vmin;
    }

    @Column(name = "hmax", precision = 22, scale = 0)
    public Double getHmax() {
        return this.hmax;
    }

    public void setHmax(Double hmax) {
        this.hmax = hmax;
    }

    @Column(name = "hmin", precision = 22, scale = 0)
    public Double getHmin() {
        return this.hmin;
    }

    public void setHmin(Double hmin) {
        this.hmin = hmin;
    }

}
