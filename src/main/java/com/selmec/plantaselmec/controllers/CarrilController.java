package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Cariles;
import com.selmec.plantaselmec.Models.Incidencias;
import com.selmec.plantaselmec.dto.CarrilDTO;
import com.selmec.plantaselmec.dto.IncidenciaDTO;
import com.selmec.plantaselmec.services.ICarrilesService;
import com.selmec.plantaselmec.services.IncidenciasService;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarrilController extends BaseController<Cariles, CarrilDTO> {

    static final String BasePath = "api/Carriles";
    static final String BasePathID = BasePath + "/{id}";

    @Autowired
    ICarrilesService CarrilesService;

    @RequestMapping(value = "Carriles", method = RequestMethod.GET)
    public String Carril() {
        return "Carril/index";
    }

    @RequestMapping(value = BasePath, method = RequestMethod.GET)
    @ResponseBody
    public List<CarrilDTO> Get() {
        return this.DTO(CarrilesService.GetAll(), Cariles.class, CarrilDTO.class);
    }
}
