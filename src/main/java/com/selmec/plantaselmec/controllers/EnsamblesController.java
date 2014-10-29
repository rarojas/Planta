package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.EnsambleDTO;
import com.selmec.plantaselmec.services.IEnsambleService;
import com.selmec.plantaselmec.services.IUsuariosServices;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/Ensambles")
public class EnsamblesController extends BaseControllers<Ensamble, EnsambleDTO> {

    private Logger logger = Logger.getLogger(EnsamblesController.class);

    @Autowired
    SessionFactory session;
    @Autowired
    private IUsuariosServices usuariosServices;

    @Autowired
    IEnsambleService ensambleServices;

    @RequestMapping("/Pruebas")
    public ModelAndView Pruebas() {
        return new ModelAndView("/Pruebas/index");
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public @ResponseBody
    List<EnsambleDTO> Get(Principal principal) {
        Usuarios usuarios = usuariosServices.GetByUsername(principal.getName());
        List<Ensamble> pruebas = ensambleServices.GetByUser(usuarios);
        return DTO(pruebas, Ensamble.class, EnsambleDTO.class);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public @ResponseBody
    EnsambleDTO Get(@PathVariable("id") int id) {
        return Get(id, Ensamble.class, EnsambleDTO.class);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public EnsambleDTO Post(@RequestBody Ensamble Ensamble, Principal principal) {
        DateFormat df = new SimpleDateFormat("yyMMddss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        logger.info(principal.getName());
        Usuarios usuario = usuariosServices.GetByUsername(principal.getName());
        Ensamble.setUsuarios(usuario);
        Ensamble.setFolio(reportDate);
        sessionFactory.getCurrentSession().save(Ensamble);
        return DTO(Ensamble, Ensamble.class, EnsambleDTO.class);

    }
}
