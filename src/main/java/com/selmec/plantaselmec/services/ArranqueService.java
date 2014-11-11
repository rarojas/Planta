package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Arranque;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrojase
 */
@Service
public class ArranqueService extends BaseServices<Arranque, Integer> implements IArranqueService {
    
    @Autowired
    public void setDao(IGenericDao<Arranque, Integer> dao) {
        this.dao = dao;
        this.dao.setClazz(Arranque.class);
    }
}
