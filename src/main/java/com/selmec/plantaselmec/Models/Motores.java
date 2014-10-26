package com.selmec.plantaselmec.Models;
// Generated Oct 21, 2014 4:26:41 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Motores generated by hbm2java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "motores", catalog = "test"
)
public class Motores implements java.io.Serializable {

    private String modelo;
    private int frecuenciaOperacion;
    private int kw;
    private int kva;
    private int amp;
    private int noFases;
    private String marca;
    private int kwContinuo;
    private int kvaContinuo;
    private int ampContinuo;
    private int combustible;
    private int tipoControl;
    private int rpm;
    private String generadorMarca;
    private String generadorModelo;
    private float derrateo;
    private Set plantas = new HashSet(0);

    public Motores() {
    }

    public Motores(String modelo, int frecuenciaOperacion, int kw, int kva, int amp, int noFases, String marca, int kwContinuo, int kvaContinuo, int ampContinuo, int combustible, int tipoControl, int rpm, String generadorMarca, String generadorModelo, float derrateo) {
        this.modelo = modelo;
        this.frecuenciaOperacion = frecuenciaOperacion;
        this.kw = kw;
        this.kva = kva;
        this.amp = amp;
        this.noFases = noFases;
        this.marca = marca;
        this.kwContinuo = kwContinuo;
        this.kvaContinuo = kvaContinuo;
        this.ampContinuo = ampContinuo;
        this.combustible = combustible;
        this.tipoControl = tipoControl;
        this.rpm = rpm;
        this.generadorMarca = generadorMarca;
        this.generadorModelo = generadorModelo;
        this.derrateo = derrateo;
    }

    public Motores(String modelo, int frecuenciaOperacion, int kw, int kva, int amp, int noFases, String marca, int kwContinuo, int kvaContinuo, int ampContinuo, int combustible, int tipoControl, int rpm, String generadorMarca, String generadorModelo, float derrateo, Set plantas) {
        this.modelo = modelo;
        this.frecuenciaOperacion = frecuenciaOperacion;
        this.kw = kw;
        this.kva = kva;
        this.amp = amp;
        this.noFases = noFases;
        this.marca = marca;
        this.kwContinuo = kwContinuo;
        this.kvaContinuo = kvaContinuo;
        this.ampContinuo = ampContinuo;
        this.combustible = combustible;
        this.tipoControl = tipoControl;
        this.rpm = rpm;
        this.generadorMarca = generadorMarca;
        this.generadorModelo = generadorModelo;
        this.derrateo = derrateo;
        this.plantas = plantas;
    }

    @Id

    @Column(name = "Modelo", unique = true, nullable = false, length = 50)
    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Column(name = "FrecuenciaOperacion", nullable = false)
    public int getFrecuenciaOperacion() {
        return this.frecuenciaOperacion;
    }

    public void setFrecuenciaOperacion(int frecuenciaOperacion) {
        this.frecuenciaOperacion = frecuenciaOperacion;
    }

    @Column(name = "KW", nullable = false)
    public int getKw() {
        return this.kw;
    }

    public void setKw(int kw) {
        this.kw = kw;
    }

    @Column(name = "KVA", nullable = false)
    public int getKva() {
        return this.kva;
    }

    public void setKva(int kva) {
        this.kva = kva;
    }

    @Column(name = "AMP", nullable = false)
    public int getAmp() {
        return this.amp;
    }

    public void setAmp(int amp) {
        this.amp = amp;
    }

    @Column(name = "NoFases", nullable = false)
    public int getNoFases() {
        return this.noFases;
    }

    public void setNoFases(int noFases) {
        this.noFases = noFases;
    }

    @Column(name = "Marca", nullable = false, length = 50)
    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Column(name = "KW_Continuo", nullable = false)
    public int getKwContinuo() {
        return this.kwContinuo;
    }

    public void setKwContinuo(int kwContinuo) {
        this.kwContinuo = kwContinuo;
    }

    @Column(name = "KVA_Continuo", nullable = false)
    public int getKvaContinuo() {
        return this.kvaContinuo;
    }

    public void setKvaContinuo(int kvaContinuo) {
        this.kvaContinuo = kvaContinuo;
    }

    @Column(name = "AMP_Continuo", nullable = false)
    public int getAmpContinuo() {
        return this.ampContinuo;
    }

    public void setAmpContinuo(int ampContinuo) {
        this.ampContinuo = ampContinuo;
    }

    @Column(name = "Combustible", nullable = false)
    public int getCombustible() {
        return this.combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    @Column(name = "TipoControl", nullable = false)
    public int getTipoControl() {
        return this.tipoControl;
    }

    public void setTipoControl(int tipoControl) {
        this.tipoControl = tipoControl;
    }

    @Column(name = "RPM", nullable = false)
    public int getRpm() {
        return this.rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    @Column(name = "GeneradorMarca", nullable = false, length = 50)
    public String getGeneradorMarca() {
        return this.generadorMarca;
    }

    public void setGeneradorMarca(String generadorMarca) {
        this.generadorMarca = generadorMarca;
    }

    @Column(name = "GeneradorModelo", nullable = false, length = 45)
    public String getGeneradorModelo() {
        return this.generadorModelo;
    }

    public void setGeneradorModelo(String generadorModelo) {
        this.generadorModelo = generadorModelo;
    }

    @Column(name = "Derrateo", nullable = false, precision = 12, scale = 0)
    public float getDerrateo() {
        return this.derrateo;
    }

    public void setDerrateo(float derrateo) {
        this.derrateo = derrateo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motores")
    public Set getPlantas() {
        return this.plantas;
    }

    public void setPlantas(Set plantas) {
        this.plantas = plantas;
    }

}
