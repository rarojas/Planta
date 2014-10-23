package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Dao.IGenericDao;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuariosServices implements IUsuariosServices {

    private IGenericDao<Usuarios, Integer> dao;

    @Autowired
    public void setDao(IGenericDao<Usuarios, Integer> daoToSet) {
        this.dao = daoToSet;
        this.dao.setClazz(Usuarios.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuarios GetUsuario(int id) {
        return (Usuarios) this.dao.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuarios GetByUsername(String username) {
        List<Usuarios> usuarios = this.dao.getCurrentSession().createCriteria(Usuarios.class).add(Restrictions.eq("email", username)).list();
        if (usuarios.isEmpty()) {
            return null;
        }
        return (Usuarios) usuarios.get(0);
    }

    @Transactional
    @Override
    public void Delete(int id) {
        this.dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuarios> All() {
        return this.dao.findAll();
    }

    /**
     *
     * @param usuarios
     */
    @Transactional
    @Override
    public void Update(Usuarios usuarios) {
        this.dao.update(usuarios);
    }

    /**
     *
     * @param usuarios
     */
    @Transactional
    @Override
    public void Save(Usuarios usuarios) {
        String password = KeyGenerators.string().generateKey();
        usuarios.setPassword(password);
        this.dao.create(usuarios);
    }
    public UsuarioDTO ToDTO(Usuarios usuario) {
        return mapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> ToDTO(List<Usuarios> usuarios) {
        List<UsuarioDTO> result = new ArrayList<>();
        for (Usuarios usuario : usuarios) {
            result.add(ToDTO(usuario));
        }
        return result;
    }

    @Autowired
    private MapperFacade mapper;
}
