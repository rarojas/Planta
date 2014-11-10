/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dchavez
 */
@Controller
@RequestMapping("/reportes/pruebas")
public class ReportesController {

    @Autowired
    @Qualifier("dataSourceMySQL")
    DataSource datasource;

    @RequestMapping(value = "/test-sumary/pdf/{id}", method = RequestMethod.GET)
    public ModelAndView doTestSummaryReport(@PathVariable("id") String id) {
        ModelAndView result = null;
        System.out.println("--->");
        Connection conn = null;
        try {

            conn = datasource.getConnection("ricardo", "ricardo");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(conn);
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("planta_id", id);
        parameterMap.put("REPORT_CONNECTION", conn);

        result = new ModelAndView("testSummaryPdfReport", parameterMap);
        return result;
    }
}
