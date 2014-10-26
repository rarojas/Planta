package com.selmec.plantaselmec.services;

import com.selmec.Utils.Description;
import com.selmec.plantaselmec.Models.Cariles;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.TablaLecturaDTO;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlantaServices implements IPlantaServices {

    @Autowired
    private JdbcTemplate jdbctemplate;
    @Autowired
    SessionFactory sessionFactory;
    
   

    @Transactional(value = "transactionManagerInformix", readOnly = true, isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public LecturaPSC LecturaPlanta(String  Equipo) {
        
        LecturaPSC result = new LecturaPSC();
        result.Time = new Date().toString();
        String sql = "select tagvalue,tagname from tablalectura where tagname like '%" + Equipo+ "|%';";

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

        return result;
    }

}
