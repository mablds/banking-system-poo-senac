/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author Marcelo
 */
public class ContaPoupanca extends Conta {

    public ContaPoupanca(int id, int cliente, String dataCriacao, int tipo, double saldo, boolean ativa) {
        super(id, cliente, dataCriacao, saldo, 3, ativa);
    }

    public ContaPoupanca(int cliente, int tipo, boolean ativa) {
        super(cliente, 3, ativa);
    }

    @JsonCreator
    public ContaPoupanca(
        @JsonProperty("cliente") int cliente,
        @JsonProperty("ativa") boolean ativa
    ) {
        super(cliente, 3, ativa);
    }

    public boolean Resgate(double valor, int idReceptor) {

        return true;
    }

}
