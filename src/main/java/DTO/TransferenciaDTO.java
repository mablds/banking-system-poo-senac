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
public class TransferenciaDTO {

    private final int idPagador;
    private final int idReceptor;
    private final double valor;

    @JsonCreator
    public TransferenciaDTO(
        @JsonProperty("idPagador") int idPagador,
        @JsonProperty("idReceptor") int idReceptor,
        @JsonProperty("valor") double valor
    ) {
        this.idPagador = idPagador;
        this.idReceptor = idReceptor;
        this.valor = valor;
    }

    public int getIdPagador() {
        return idPagador;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public double getValor() {
        return valor;
    }
    
    
}
