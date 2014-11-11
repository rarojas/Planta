/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Arranque;
import com.selmec.plantaselmec.dto.ArranqueDTO;
import com.selmec.plantaselmec.services.IArranqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("/api/Arranque")
public class ArranqueController extends BaseController<Arranque, Integer, ArranqueDTO> {

    IArranqueService arranqueService;

    @Autowired
    public void setArranqueService(IArranqueService arranqueService) {
        this.baseService = arranqueService;
        this.arranqueService = arranqueService;
        this.DTO = ArranqueDTO.class;
    }

}
