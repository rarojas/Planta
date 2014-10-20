package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.Prueba;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rrojase
 */
public class EnsambleDTO implements java.io.Serializable {

    public EnsambleDTO() {
    }

    public EnsambleDTO(Ensamble ensamble) {
        this.id = ensamble.getId();
        this.folio = ensamble.getFolio();
        if (ensamble.getPlanta() != null) {
            this.planta = new PlantaDTO(ensamble.getPlanta());
        }
        this.altitud = ensamble.getAltitud();
        this.carriles = new CarrilDTO(ensamble.getCariles());
        this.guardas = ensamble.getGuardas();
        this.patin = ensamble.getPatin();
        this.rediador = ensamble.getRediador();
        if (ensamble.getUsuarios() != null) {
            this.usuarioId = new UsuarioDTO(ensamble.getUsuarios());
        }
        this.pruebas = new ArrayList<>();
        for (Object prueba : ensamble.getPruebas()) {
            this.pruebas.add(new PruebaDTO((Prueba) prueba));
        }
    }

    public EnsambleDTO(Ensamble ensamble, List<PruebaDTO> pruebas) {
        this.pruebas = pruebas;
        this.id = ensamble.getId();
        this.folio = ensamble.getFolio();
        if (ensamble.getPlanta() != null) {
            this.planta = new PlantaDTO(ensamble.getPlanta());
        }
        this.altitud = ensamble.getAltitud();
        this.carriles = new CarrilDTO(ensamble.getCariles());
        this.guardas = ensamble.getGuardas();
        this.patin = ensamble.getPatin();
        this.rediador = ensamble.getRediador();
        if (ensamble.getUsuarios() != null) {
            this.usuarioId = new UsuarioDTO(ensamble.getUsuarios());
        }
    }

    public int id;
    public PlantaDTO planta;
    public String folio;
    public UsuarioDTO usuarioId;
    public int dtCreacion;
    public CarrilDTO carriles;
    public int altitud;
    public String rediador;
    public String patin;
    public String guardas;
    public List<PruebaDTO> pruebas;
}
