package com.selmec.plantaselmec.services;

import com.selmec.Utils.Description;
import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.PlantaDTO;
import com.selmec.plantaselmec.dto.TablaLecturaDTO;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Configurable
public class PlantaServices extends BaseServices<Planta, String> implements IPlantaServices {

    @Autowired
    public void setDao(IGenericDao<Planta, String> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Planta.class);
    }

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Transactional(value = "transactionManagerInformix", readOnly = true, isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public LecturaPSC LecturaPlanta(String Equipo) {

        LecturaPSC result = new LecturaPSC();
        result.Time = new Date().toString();
        String sql = "select tagvalue,tagname from tablalectura where tagname like '%" + Equipo + "|%';";

        List<TablaLecturaDTO> results = jdbctemplate.query(sql, new BeanPropertyRowMapper(TablaLecturaDTO.class));
        Field[] fields;
        fields = LecturaPSC.class.getDeclaredFields();
        for (Field field : fields) {
            Description description = field.getAnnotation(Description.class);
            if (description != null) {
                for (TablaLecturaDTO r : results) {
                    if (r.tagname.contains(description.value())) {
                        if (description.value().contains("CORRIENTE")) {
                            try {
                                if (field.get(result) == null) {
                                    field.set(result, r.tagvalue);
                                } else {
                                    field.set(result, field.getDouble(result) + r.tagvalue);
                                }
                            } catch (IllegalArgumentException | IllegalAccessException ex) {
                                Logger.getLogger(PlantaServices.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                field.set(result, r.tagvalue);
                            } catch (IllegalArgumentException | IllegalAccessException ex) {
                                Logger.getLogger(PlantaServices.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
        if (result.Presion != null) {
            result.Presion *= 14.5038;/// Conversión de bar PSI
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlantaDTO> GetPlantas() {
        return mapper.mapAsList(dao.findAll(), PlantaDTO.class);
    }

    @Autowired
    MapperFacade mapper;

    @Transactional
    @Override
    public List<PlantaDTO> GetPlantaByOP(String noOP) {
        return mapper.mapAsList(dao.getCurrentSession().createCriteria(Planta.class)
                .add(Restrictions.like("noOp", "%" + noOP + "%")).list(), PlantaDTO.class);
    }

}
