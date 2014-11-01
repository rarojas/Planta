/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.Ensamblearranque;
import com.selmec.plantaselmec.services.IEnsamblearranqueServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/EnsambleArranque")
public class EnsambleArranqueController extends BaseController<Ensamblearranque, Integer, Ensamble> {

    IEnsamblearranqueServices ensambleService;

    public void ensambleService(IEnsamblearranqueServices ensambleService) {
        this.baseService = ensambleService;
        this.ensambleService = ensambleService;
    }
}
