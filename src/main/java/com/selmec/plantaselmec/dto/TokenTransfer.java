/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

/**
 *
 * @author rrojase
 */
public class TokenTransfer {

    private final String token;

    public TokenTransfer(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
