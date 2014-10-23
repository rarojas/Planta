package com.selmec.plantaselmec.controllers;

import com.selmec.Utils.RandomGenerator;
import com.selmec.plantaselmec.Models.Lecturas;
import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.dto.ErrorResource;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.TablaLecturaDTO;
import com.selmec.plantaselmec.dto.ValoresEsperados;
import com.selmec.plantaselmec.services.IEnsambleService;
import com.selmec.plantaselmec.services.ILecturasService;
import com.selmec.plantaselmec.services.IPlantaServices;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/Planta")
public class PlantaController {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    IEnsambleService ensambleService;

    @Autowired
    IPlantaServices PlantaServices;
    @Autowired
    ILecturasService LecturasService;

    private final Logger logger = Logger.getLogger(PlantaController.class);
    @Autowired
    JdbcTemplate jdbctemplate;

    @RequestMapping(value = "Planta", method = RequestMethod.GET)
    public String Index() {
        return "Plantas/index";
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Planta Post(@RequestBody Planta planta) {
        sessionFactory.getCurrentSession().persist(planta);
        return planta;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Planta> Get() {
        return sessionFactory.getCurrentSession().createCriteria(Planta.class).list();
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Planta Get(@RequestParam String id) {
        return (Planta) sessionFactory.getCurrentSession().get(Planta.class, id);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/Valores/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ValoresEsperados EsperadoSC(@PathVariable String id) {
        Planta Planta = (Planta) sessionFactory.getCurrentSession().get(Planta.class, id);
        ValoresEsperados valores = new ValoresEsperados();
        Double corriente;
        corriente = ((1000 * Planta.getMotores().getKw()) / Planta.getVoltaje()) * (100 - Planta.getMotores().getDerrateo()) * 0.01;
        valores.Max.I3 = valores.Max.I2 = valores.Max.I1 = corriente * 1.01;
        valores.Min.I3 = valores.Min.I2 = valores.Min.I1 = corriente * 0.99;
        valores.Min.L3L1 = valores.Min.L2L3 = valores.Min.L1L2 = Planta.getVoltaje() * 0.99;
        valores.Max.L3L1 = valores.Max.L2L3 = valores.Max.L1L2 = Planta.getVoltaje() * 1.01;
        valores.Min.RMP = Planta.getMotores().getRpm() * 0.99;
        valores.Max.RMP = Planta.getMotores().getRpm() * 1.01;
        valores.Min.HZ = Planta.getMotores().getFrecuenciaOperacion() * 0.975;
        valores.Max.HZ = Planta.getMotores().getFrecuenciaOperacion() * 1.025;
        return valores;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable("id") int id) {
        Planta Planta = (Planta) sessionFactory.getCurrentSession().get(Planta.class, id);
        sessionFactory.getCurrentSession().delete(Planta);
    }

    @Transactional
    @RequestMapping(value = "On/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String On(@PathVariable("id") int id) {
        logger.info(ensambleService);
        ensambleService.TurnOnCarril(id);
        return "true";
    }

    @Transactional
    @RequestMapping(value = "SPControl/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String SPControl(@PathVariable("id") int id) {
        ensambleService.ExcuteSPControl(id);
        return "true";
    }

    @Transactional
    @RequestMapping(value = "Off/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String Off(@PathVariable("id") int id) {
        logger.info(ensambleService);
        ensambleService.TurnOffCarril(id);
        return "true";
    }

    @RequestMapping(value = "/GetValues/{id}/{seg}/{ite}", method = RequestMethod.GET)
    public @ResponseBody
    LecturaPSC GetValues(@PathVariable("id") int id, @PathVariable("seg") int seg, @PathVariable("ite") int ite) {
        long start = System.currentTimeMillis();    
        LecturaPSC result = PlantaServices.LecturaPlanta(id);
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info(elapsedTime);
        result.segundo = seg;
        result.iteracion = ite;
        LecturasService.Save(result, id);
        return result;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResource handleSQLException(HttpServletRequest request, Exception ex) {
        logger.info("SQLException Occured:: URL=" + request.getRequestURL());
        SQLException sqlException = (SQLException) ex;
        ErrorResource error = new ErrorResource();
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.setMessage(sqlException.getMessage());
        return error;
    }
}
