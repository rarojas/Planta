/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Pruebacontrol;
import com.selmec.plantaselmec.services.IPruebacontrolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/Pruebacontrol")
public class PruebacontrolController {

    @Autowired
    IPruebacontrolServices pruebacontrolServices;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void Post(@RequestBody Pruebacontrol prueba) {
        pruebacontrolServices.Save(prueba);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void Put(@RequestBody Pruebacontrol prueba) {
        pruebacontrolServices.Save(prueba);
    }
}
