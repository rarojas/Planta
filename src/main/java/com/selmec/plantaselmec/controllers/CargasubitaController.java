/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Cargasubita;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.plantaselmec.dto.PruebaDTO;
import com.selmec.plantaselmec.services.ICargasubitaService;
import com.selmec.plantaselmec.services.IMotoresService;
import java.util.List;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author GEIDAR
 */
@Controller
@RequestMapping("/api/Cargasubita")
public class CargasubitaController extends BaseController<Cargasubita, CargasubitaDTO> {

    @Autowired
    ICargasubitaService CargaSubitaService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<CargasubitaDTO> Get() {
        return DTO(CargaSubitaService.GetAll(), Cargasubita.class, CargasubitaDTO.class);
    }

    @Transactional(readOnly = true)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CargasubitaDTO Get(@PathVariable("id") int id) {
        return Get(id, Cargasubita.class, CargasubitaDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Cargasubita Post(@Valid @RequestBody Cargasubita cargaSubita) {
        CargaSubitaService.Save(cargaSubita);
        return cargaSubita;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    Cargasubita Put(@Valid @RequestBody Cargasubita cargaSubita) {
        CargaSubitaService.Update(cargaSubita);
        return cargaSubita;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void Delete(@PathVariable Integer id) {
        CargaSubitaService.Delete(id);
    }

}
