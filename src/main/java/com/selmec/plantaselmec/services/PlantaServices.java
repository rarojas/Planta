package com.selmec.plantaselmec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlantaServices
{
  @Autowired
  private JdbcTemplate jdbctemplate;
  
  public void LecturaPlanta(int PruebaId) {}
}


/* Location:           C:\Users\rrojase\Desktop\Planta\WEB-INF\classes\
 * Qualified Name:     com.selmec.plantaselmec.services.PlantaServices
 * JD-Core Version:    0.7.0.1
 */