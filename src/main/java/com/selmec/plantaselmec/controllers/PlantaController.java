package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.PlantaDTO;
import com.selmec.plantaselmec.dto.ValoresEsperados;
import com.selmec.plantaselmec.services.IEnsambleService;
import com.selmec.plantaselmec.services.ILecturasService;
import com.selmec.plantaselmec.services.IPlantaServices;
import com.selmec.plantaselmec.services.IPruebaServices;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/Planta")
public class PlantaController extends BaseController<Planta, String, PlantaDTO> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    IEnsambleService ensambleService;

    IPlantaServices PlantaServices;

    @Autowired
    ILecturasService LecturasService;

    private final Logger logger = Logger.getLogger(PlantaController.class);

    @RequestMapping(value = "Planta", method = RequestMethod.GET)
    public String Index() {
        return "Plantas/index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    @Override
    List<PlantaDTO> Get() {
        return PlantaServices.GetPlantas();
    }

    @RequestMapping(value = "ByOp", method = RequestMethod.GET)
    public @ResponseBody
    List<PlantaDTO> GetByOp(@RequestParam("noOP") String noOP) {
        return PlantaServices.GetPlantaByOP(noOP);
    }

//    @Transactional
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
//    public Planta Post(@RequestBody Planta planta) {
//        sessionFactory.getCurrentSession().persist(planta);
//        return planta;
//    }
//    @Transactional
//    @RequestMapping(method = RequestMethod.GET)
//    public @ResponseBody
//    List<Planta> Get() {
//        return sessionFactory.getCurrentSession().createCriteria(Planta.class).list();
//    }
//
//    @Transactional
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public @ResponseBody
//    Planta Get(@RequestParam String id) {
//        return (Planta) sessionFactory.getCurrentSession().get(Planta.class, id);
//    }
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
        valores.Max.Presion = 50d;
        valores.Max.Temp = 98d;
        valores.Min.Presion = 1.5 * 14.5038;
        return valores;
    }

//    @Transactional
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public void Delete(@PathVariable("id") int id) {
//        Planta Planta = (Planta) sessionFactory.getCurrentSession().get(Planta.class, id);
//        sessionFactory.getCurrentSession().delete(Planta);
//    }
    @Transactional
    @RequestMapping(value = "On/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String On(@PathVariable("id") int id) {
        ensambleService.TurnOnCarril(id);
        return "true";
    }

    @Transactional
    @RequestMapping(value = "Auto/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String Auto(@PathVariable("id") int id) {
        ensambleService.TurnAutoCarril(id);
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
        ensambleService.TurnOffCarril(id);
        return "true";
    }

    @RequestMapping(value = "/GetValues/{id}/{seg}/{ite}/{equ}", method = RequestMethod.GET)
    public @ResponseBody
    LecturaPSC GetValues(@PathVariable("id") int id, @PathVariable("seg") int seg, @PathVariable("ite") int ite, @PathVariable("equ") String equipo) {
        LecturaPSC result = PlantaServices.LecturaPlanta(equipo);
        result.segundo = seg;
        result.iteracion = ite;
        long start = System.currentTimeMillis();
        LecturasService.Save(result, id);
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info(elapsedTime);
        return result;
    }

    @Autowired
    IPruebaServices pruebaServics;

    /**
     * @param PlantaServices the PlantaServices to set
     */
    @Autowired
    public void setPlantaServices(IPlantaServices PlantaServices) {
        this.PlantaServices = PlantaServices;
        this.baseService = PlantaServices;
    }
}
