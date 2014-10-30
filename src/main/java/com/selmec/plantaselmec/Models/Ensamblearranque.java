package com.selmec.plantaselmec.Models;
// Generated Oct 30, 2014 5:43:43 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Ensamblearranque generated by hbm2java
 */
@Entity
@Table(name = "ensamblearranque", catalog = "casetapruebas"
)
public class Ensamblearranque extends Ensamblebase implements java.io.Serializable {

    private Ubicaciones ubicaciones;
    private String recibio;
    private String tecnico;
    private Set pruebaarranques = new HashSet(0);

    public Ensamblearranque() {
    }


//    @OneToOne(fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    public Ensamblebase getEnsamblebase() {
//        return this.ensamblebase;
//    }
//
//    public void setEnsamblebase(Ensamblebase ensamblebase) {
//        this.ensamblebase = ensamblebase;
//    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UbicacionId", nullable = false)
    public Ubicaciones getUbicaciones() {
        return this.ubicaciones;
    }

    public void setUbicaciones(Ubicaciones ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    @Column(name = "Recibio", nullable = false, length = 100)
    public String getRecibio() {
        return this.recibio;
    }

    public void setRecibio(String recibio) {
        this.recibio = recibio;
    }

    @Column(name = "Tecnico", nullable = false, length = 100)
    public String getTecnico() {
        return this.tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ensamblearranque")
    public Set getPruebaarranques() {
        return this.pruebaarranques;
    }

    public void setPruebaarranques(Set pruebaarranques) {
        this.pruebaarranques = pruebaarranques;
    }

}
