package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Planta;

public class PlantaDTO {

    public String noSerie;
//    public MotorDTO motores;
    public String noOp;
    public String motorSerie;
    public String controlId;
    public int tipoOperacion;
    public String generadorSerie;
    public Integer capInt;
    public Integer voltaje;

    public PlantaDTO() {
    }

    PlantaDTO(Planta planta) {
        this.noSerie = planta.getNoSerie();
//        if (planta.getMotores() != null) {
//            this.motores = new MotorDTO(planta.getMotores());
//        }
        this.controlId = planta.getControlId();
        this.noOp = planta.getNoOp();
        this.motorSerie = planta.getMotorSerie();
        this.tipoOperacion = planta.getTipoOperacion();
        this.capInt = planta.getCapInt();
        this.generadorSerie = planta.getGeneradorSerie();
        this.tipoOperacion = planta.getTipoOperacion();
        this.voltaje = planta.getVoltaje();

    }
}
