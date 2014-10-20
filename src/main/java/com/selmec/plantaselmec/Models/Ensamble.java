package com.selmec.plantaselmec.Models;
// Generated 14/10/2014 10:35:51 AM by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Ensamble generated by hbm2java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="ensamble"
    ,catalog="test"
    , uniqueConstraints = @UniqueConstraint(columnNames="Folio") 
)
public class Ensamble  implements java.io.Serializable {


     private Integer id;
     private Cariles cariles;
     private Planta planta;
     private String folio;
     private int usuarioId;
     private Date dtCreacion;
     private int altitud;
     private String rediador;
     private String patin;
     private String guardas;
     private Set pruebas = new HashSet(0);

    public Ensamble() {
    }

	
    public Ensamble(Cariles cariles, Planta planta, String folio, int usuarioId, Date dtCreacion, int altitud, String rediador, String patin, String guardas) {
        this.cariles = cariles;
        this.planta = planta;
        this.folio = folio;
        this.usuarioId = usuarioId;
        this.dtCreacion = dtCreacion;
        this.altitud = altitud;
        this.rediador = rediador;
        this.patin = patin;
        this.guardas = guardas;
    }
    public Ensamble(Cariles cariles, Planta planta, String folio, int usuarioId, Date dtCreacion, int altitud, String rediador, String patin, String guardas, Set pruebas) {
       this.cariles = cariles;
       this.planta = planta;
       this.folio = folio;
       this.usuarioId = usuarioId;
       this.dtCreacion = dtCreacion;
       this.altitud = altitud;
       this.rediador = rediador;
       this.patin = patin;
       this.guardas = guardas;
       this.pruebas = pruebas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CarrilID", nullable=false)
    public Cariles getCariles() {
        return this.cariles;
    }
    
    public void setCariles(Cariles cariles) {
        this.cariles = cariles;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PlantaID", nullable=false)
    public Planta getPlanta() {
        return this.planta;
    }
    
    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    
    @Column(name="Folio", unique=true, nullable=false, length=50)
    public String getFolio() {
        return this.folio;
    }
    
    public void setFolio(String folio) {
        this.folio = folio;
    }

    
    @Column(name="UsuarioID", nullable=false)
    public int getUsuarioId() {
        return this.usuarioId;
    }
    
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dtCreacion", nullable=false, length=19)
    public Date getDtCreacion() {
        return this.dtCreacion;
    }
    
    public void setDtCreacion(Date dtCreacion) {
        this.dtCreacion = dtCreacion;
    }

    
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


