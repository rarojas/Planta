/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

/**
 *
 * @author rrojase
 */
public interface IEnsambleService {

    void TurnOnCarril(int estado);

    void TurnOffCarril(int estado);

    void ExcuteSPControl(int estado);
}
