/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Marcelo
 */
public class SaqueDTO {

    private  int id;
    private final int idConta;
    private final double valor;

    @JsonCreator
    public SaqueDTO(
        @JsonProperty("id") int id,
        @JsonProperty("idConta") int idConta,
        @JsonProperty("valor") double valor
    ) {
        this.id = id;
        this.idConta = idConta;
        this.valor = valor;
    }

    public SaqueDTO(int idConta, double valor) {
        this.idConta = idConta;
        this.valor = valor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConta() {
        return idConta;
    }

    public double getValor() {
        return valor;
    }
}