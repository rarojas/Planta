package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import java.util.List;

public interface IUsuariosServices {

    public abstract Usuarios GetUsuario(int paramInt);

    public void Delete(int paramInt);

    public List<Usuarios> All();

    public void Update(Usuarios paramUsuarios);

    public void Save(Usuarios paramUsuarios);

    public Usuarios GetByUsername(String paramString);

    List<UsuarioDTO> ToDTO(List<Usuarios> usuarios);

    UsuarioDTO ToDTO(Usuarios usuario);
}
