package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Usuarios;
import java.util.List;

public abstract interface IUsuariosServices
{
  public abstract Usuarios GetUsuario(int paramInt);
  
  public abstract void Delete(int paramInt);
  
  public abstract List<Usuarios> All();
  
  public abstract void Update(Usuarios paramUsuarios);
  
  public abstract void Save(Usuarios paramUsuarios);
  
  public abstract Usuarios GetByUsername(String paramString);
}


/* Location:           C:\Users\rrojase\Desktop\Planta\WEB-INF\classes\
 * Qualified Name:     com.selmec.plantaselmec.services.IUsuariosServices
 * JD-Core Version:    0.7.0.1
 */