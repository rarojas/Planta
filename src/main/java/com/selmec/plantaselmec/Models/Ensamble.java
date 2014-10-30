package com.selmec.plantaselmec.Models;
// Generated Oct 30, 2014 5:43:43 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Ensamble generated by hbm2java
 */
@Entity
@Table(name="ensamble"
    ,catalog="casetapruebas"
)
public class Ensamble extends Ensamblebase  implements java.io.Serializable {

     
//     private Ensamblebase ensamblebase;
     private int altitud;
     private String rediador;
     private String patin;
     private String guardas;
     private Set pruebas = new HashSet(0);

    public Ensamble() {
    }

	    
   
//     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="ensamblebase"))@Id @GeneratedValue(generator="generator")
//
//    
//    @Column(name="ID", unique=true, nullable=false)
//    public int getId() {
//        return this.id;
//    }
//    
//    public void setId(int id) {
//        this.id = id;
//    }

//@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
//    public Ensamblebase getEnsamblebase() {
//        return this.ensamblebase;
//    }
//    
//    public void setEnsamblebase(Ensamblebase ensamblebase) {
//        this.ensamblebase = ensamblebase;
//    }

    
    @Column(name="Altitud", nullable=false)
    public int getAltitud() {
        return this.altitud;
    }
    
    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    
    @Column(name="Rediador", nullable=false, length=50)
    public String getRediador() {
        return this.rediador;
    }
    
    public void setRediador(String rediador) {
        this.rediador = rediador;
    }

    
    @Column(name="Patin", nullable=false, length=50)
    public String getPatin() {
        return this.patin;
    }
    
    public void setPatin(String patin) {
        this.patin = patin;
    }

    
    @Column(name="Guardas", nullable=false, length=50)
    public String getGuardas() {
        return this.guardas;
    }
    
    public void setGuardas(String guardas) {
        this.guardas = guardas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ensamble")
    public Set getPruebas() {
        return this.pruebas;
    }
    
    public void setPruebas(Set pruebas) {
        this.pruebas = pruebas;
    }




}


