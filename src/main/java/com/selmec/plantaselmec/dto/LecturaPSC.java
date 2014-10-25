package com.selmec.plantaselmec.dto;

import com.selmec.Utils.Description;

public class LecturaPSC {

    public String Time;
    @Description("GENERADOR F1|VOLTAJE|")
    public Double L1N;
    @Description("GENERADOR F2|VOLTAJE|")
    public Double L2N;
    @Description("GENERADOR F3|VOLTAJE|")
    public Double L3N;
    @Description("GENERADOR F1|VOLTAJE|")
    public Double L1L2;
    @Description("GENERADOR F2|VOLTAJE|")
    public Double L2L3;
    @Description("GENERADOR F3|VOLTAJE|")
    public Double L3L1;
    @Description("GENERADOR|FRECUENCIA")
    public Double HZ;
    @Description("MOTOR|T REFRIGERANTE")
    public Double Temp;
    @Description("MOTOR|PRESION ACEITE")
    public Double Presion;
    @Description("GENERADOR F1|CORRIENTE|")
    public Double I1;
    @Description("GENERADOR F2|CORRIENTE|")
    public Double I2;
    @Description("GENERADOR F3|CORRIENTE|")
    public Double I3;
    @Description("GENERADOR|RPM")
    public Double RMP;
    @Description("GENERADOR|RPM")
    public Double Timer;
    @Description("MOTOR BATERIA|VOLTAJE")
    public Double bateria;
    public int segundo;
    public int iteracion;

    public LecturaPSC() {
    }

}
