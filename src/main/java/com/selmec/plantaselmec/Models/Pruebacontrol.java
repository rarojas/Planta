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
 * Pruebacontrol generated by hbm2java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "pruebacontrol", catalog = "test"
)
@PrimaryKeyJoinColumn(name = "id")
public class Pruebacontrol
        extends Prueba implements java.io.Serializable {

//    private int id;
//    private Prueba prueba;
    private int termometro;
    private Integer presion;
    private Integer saquemarcha;
    private String sobrevelocidad;
    private String ubt;
    private Boolean operacionubt;
    private Boolean cargaliena;
    private Boolean altatemperatura;
    private Boolean bajapresion;
    private Boolean fallageneral;
    private Boolean arranquemanual;
    private Boolean bajepresion;
    private Boolean temperatura;
    private Boolean proteccionsobrevelocidad;
    private Integer intentosarranque;
    private Integer duraciontotal;
    private String pruebacontrolcol;

    public Pruebacontrol() {
    }

    public Pruebacontrol(//Prueba prueba,
            int termometro) {
        // this.prueba = prueba;
        this.termometro = termometro;
    }

    public Pruebacontrol(//Prueba prueba,
            int termometro, Integer presion, Integer saquemarcha, String sobrevelocidad, String ubt, Boolean operacionubt, Boolean cargaliena, Boolean altatemperatura, Boolean bajapresion, Boolean fallageneral, Boolean arranquemanual, Boolean bajepresion, Boolean temperatura, Boolean proteccionsobrevelocidad, Integer intentosarranque, Integer duraciontotal, String pruebacontrolcol) {
        //this.prueba = prueba;
        this.termometro = termometro;
        this.presion = presion;
        this.saquemarcha = saquemarcha;
        this.sobrevelocidad = sobrevelocidad;
        this.ubt = ubt;
        this.operacionubt = operacionubt;
        this.cargaliena = cargaliena;
        this.altatemperatura = altatemperatura;
        this.bajapresion = bajapresion;
        this.fallageneral = fallageneral;
        this.arranquemanual = arranquemanual;
        this.bajepresion = bajepresion;
        this.temperatura = temperatura;
        this.proteccionsobrevelocidad = proteccionsobrevelocidad;
        this.intentosarranque = intentosarranque;
        this.duraciontotal = duraciontotal;
        this.pruebacontrolcol = pruebacontrolcol;
    }

//    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "prueba"))
//    @Id
//    @GeneratedValue(generator = "generator")
//
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
    @Column(name = "termometro", nullable = false)
    public int getTermometro() {
        return this.termometro;
    }

    public void setTermometro(int termometro) {
        this.termometro = termometro;
    }

    @Column(name = "presion")
    public Integer getPresion() {
        return this.presion;
    }

    public void setPresion(Integer presion) {
        this.presion = presion;
    }

    @Column(name = "saquemarcha")
    public Integer getSaquemarcha() {
        return this.saquemarcha;
    }

    public void setSaquemarcha(Integer saquemarcha) {
        this.saquemarcha = saquemarcha;
    }

    @Column(name = "sobrevelocidad", length = 45)
    public String getSobrevelocidad() {
        return this.sobrevelocidad;
    }

    public void setSobrevelocidad(String sobrevelocidad) {
        this.sobrevelocidad = sobrevelocidad;
    }

    @Column(name = "ubt", length = 45)
    public String getUbt() {
        return this.ubt;
    }

    public void setUbt(String ubt) {
        this.ubt = ubt;
    }

    @Column(name = "operacionubt")
    public Boolean getOperacionubt() {
        return this.operacionubt;
    }

    public void setOperacionubt(Boolean operacionubt) {
        this.operacionubt = operacionubt;
    }

    @Column(name = "cargaliena")
    public Boolean getCargaliena() {
        return this.cargaliena;
    }

    public void setCargaliena(Boolean cargaliena) {
        this.cargaliena = cargaliena;
    }

    @Column(name = "altatemperatura")
    public Boolean getAltatemperatura() {
        return this.altatemperatura;
    }

    public void setAltatemperatura(Boolean altatemperatura) {
        this.altatemperatura = altatemperatura;
    }

    @Column(name = "bajapresion")
    public Boolean getBajapresion() {
        return this.bajapresion;
    }

    public void setBajapresion(Boolean bajapresion) {
        this.bajapresion = bajapresion;
    }

    @Column(name = "fallageneral")
    public Boolean getFallageneral() {
        return this.fallageneral;
    }

    public void setFallageneral(Boolean fallageneral) {
        this.fallageneral = fallageneral;
    }

    @Column(name = "arranquemanual")
    public Boolean getArranquemanual() {
        return this.arranquemanual;
    }

    public void setArranquemanual(Boolean arranquemanual) {
        this.arranquemanual = arranquemanual;
    }

    @Column(name = "bajepresion")
    public Boolean getBajepresion() {
        return this.bajepresion;
    }

    public void setBajepresion(Boolean bajepresion) {
        this.bajepresion = bajepresion;
    }

    @Column(name = "temperatura")
    public Boolean getTemperatura() {
        return this.temperatura;
    }

    public void setTemperatura(Boolean temperatura) {
        this.temperatura = temperatura;
    }

    @Column(name = "proteccionsobrevelocidad")
    public Boolean getProteccionsobrevelocidad() {
        return this.proteccionsobrevelocidad;
    }

    public void setProteccionsobrevelocidad(Boolean proteccionsobrevelocidad) {
        this.proteccionsobrevelocidad = proteccionsobrevelocidad;
    }

    @Column(name = "intentosarranque")
    public Integer getIntentosarranque() {
        return this.intentosarranque;
    }

    public void setIntentosarranque(Integer intentosarranque) {
        this.intentosarranque = intentosarranque;
    }

    @Column(name = "duraciontotal")
    public Integer getDuraciontotal() {
        return this.duraciontotal;
    }

    public void setDuraciontotal(Integer duraciontotal) {
        this.duraciontotal = duraciontotal;
    }

    @Column(name = "pruebacontrolcol", length = 45)
    public String getPruebacontrolcol() {
        return this.pruebacontrolcol;
    }

    public void setPruebacontrolcol(String pruebacontrolcol) {
        this.pruebacontrolcol = pruebacontrolcol;
    }

}
