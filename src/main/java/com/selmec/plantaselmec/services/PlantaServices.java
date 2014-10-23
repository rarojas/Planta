package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.dto.LecturaPSC;
import com.selmec.plantaselmec.dto.TablaLecturaDTO;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlantaServices implements IPlantaServices {

    @Autowired
    private JdbcTemplate jdbctemplate;
    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public LecturaPSC LecturaPlanta(int PruebaId) {

        Prueba prueba = (Prueba) sessionFactory.getCurrentSession().get(Prueba.class, PruebaId);
        LecturaPSC result = new LecturaPSC();
        result.Time = new Date().toString();
        String sql = "select limit 1 * from tablalectura where tagname like '%" + prueba.getEnsamble().getCariles().getEquipo() + "|";

        TablaLecturaDTO read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "FASE 1|VOLTAJE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.L1N = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "FASE 2|VOLTAJE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.L2N = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "FASE 3|VOLTAJE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.L3N = read.tagvalue;

        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "FASE 1|CORRIENTE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.I1 = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "FASE 2|CORRIENTE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.I2 = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "FASE 3|CORRIENTE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.I3 = read.tagvalue;

        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "MOTOR|PRESION ACEITE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.Presion = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "MOTOR|TEMPERATURA%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.Temp = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "FRECUENCIA|FREQUENCY%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.HZ = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "GENERADOR|RPM%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.RMP = read.tagvalue;
        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "GENERADOR|TIMER%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
        result.Timer = read.tagvalue;
//        read = (TablaLecturaDTO) jdbctemplate.queryForObject(sql + "GENERADOR|VOLTAJE%'", new BeanPropertyRowMapper(TablaLecturaDTO.class));
//        result.bateria = read.tagvalue;
        return result;
    }

}
