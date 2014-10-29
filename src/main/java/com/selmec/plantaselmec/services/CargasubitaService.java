package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Cargasubita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GEIDAR
 */
@Service
public class CargasubitaService extends BaseServices<Cargasubita, Integer> implements ICargasubitaService {


    @Autowired
    public void setDao(IGenericDao< Cargasubita, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Cargasubita.class);
    }
    
}
