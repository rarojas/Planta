package com.selmec.plantaselmec.Models;
// Generated Oct 21, 2014 4:26:41 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Cargasubita generated by hbm2java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "cargasubita", catalog = "test"
)
@PrimaryKeyJoinColumn(name="id")
public class Cargasubita extends Prueba implements java.io.Serializable {

//    private int id;
//    private Prueba prueba;
    private Integer seg;
    private Double vinicio;
    private Double vfinal;
    private Double hfinal;
    private Double hinicio;
    private Double icarga;

    public Cargasubita() {
    }

    public Cargasubita(Prueba prueba) {
//        this.prueba = prueba;
    }

    public Cargasubita(Prueba prueba, Integer seg, Double vinicio, Double vfinal, Double hfinal, Double hinicio, Double icarga) {
       //F this.prueba = prueba;
        this.seg = seg;
        this.vinicio = vinicio;
        this.vfinal = vfinal;
        this.hfinal = hfinal;
        this.hinicio = hinicio;
        this.icarga = icarga;
    }
//
//    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "prueba"))
//    @Id
//    @GeneratedValue(generator = "generator")
//    @Column(name = "ID", unique = true, nullable = false)
//    public int getId() {
//        return this.id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @OneToOne
//    @PrimaryKeyJoinColumn
//    public Prueba getPrueba() {
//        return this.prueba;
//    }
//
//    public void setPrueba(Prueba prueba) {
//        this.prueba = prueba;
//    }

    @Column(name = "seg")
    public Integer getSeg() {
        return this.seg;
    }

    public void setSeg(Integer seg) {
        this.seg = seg;
    }

    @Column(name = "vinicio", precision = 22, scale = 0)
    public Double getVinicio() {
        return this.vinicio;
    }

    public void setVinicio(Double vinicio) {
        this.vinicio = vinicio;
    }

    @Column(name = "vfinal", precision = 22, scale = 0)
    public Double getVfinal() {
        return this.vfinal;
    }

    public void setVfinal(Double vfinal) {
        this.vfinal = vfinal;
    }

    @Column(name = "hfinal", precision = 22, scale = 0)
    public Double getHfinal() {
        return this.hfinal;
    }

    public void setHfinal(Double hfinal) {
        this.hfinal = hfinal;
    }

    @Column(name = "hinicio", precision = 22, scale = 0)
    public Double getHinicio() {
        return this.hinicio;
    }

    public void setHinicio(Double hinicio) {
        this.hinicio = hinicio;
    }

    @Column(name = "icarga", precision = 22, scale = 0)
    public Double getIcarga() {
        return this.icarga;
    }

    public void setIcarga(Double icarga) {
        this.icarga = icarga;
    }

}
