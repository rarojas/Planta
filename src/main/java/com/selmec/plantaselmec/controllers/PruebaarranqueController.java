/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Pruebaarranque;
import com.selmec.plantaselmec.services.IPruebaarranqueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/Pruebasarranque")
public class PruebaarranqueController extends BaseController<Pruebaarranque, Integer, Pruebaarranque> {

    IPruebaarranqueService PruebaService;

    public void PruebaService(IPruebaarranqueService PruebaService) {
        this.baseService = PruebaService;
        this.PruebaService = PruebaService;
    }
}
