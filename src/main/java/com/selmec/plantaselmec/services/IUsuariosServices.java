package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.UsuarioActualDTO;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import java.util.List;

public interface IUsuariosServices {

    public abstract Usuarios GetUsuario(int paramInt);

    public void Delete(int paramInt);

    public List<Usuarios> All();

    public void Update(Usuarios paramUsuarios);

    public void Save(Usuarios paramUsuarios);

    public Usuarios GetByUsername(String paramString);

    List<UsuarioDTO> ToDTO(List<Usuarios> usuarios);//supongo que se debe de eliminar este método

    UsuarioDTO ToDTO(Usuarios usuario);//igual supongo que hay que quitar este método
    
    public UsuarioActualDTO getDataUser(String userName);
    
}
