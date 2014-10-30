/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

/**
 *
 * @author GEIDAR
 */
public class UbicacionDTO implements java.io.Serializable {

    public int id;
    //public ClientesDTO clientes; //se hace referencia circular?, supongo que sí
    public String poblacion;
    public String estado;
    public String telefono;
    //public Set ensambles = new HashSet(0); //se hace referencia circular?, supongo que sí

    public UbicacionDTO() {
    }
}
