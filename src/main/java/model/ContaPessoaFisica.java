/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Marcelo
 */
public class ContaPessoaFisica extends Conta implements Operacoes {

    public ContaPessoaFisica(int id, int cliente, String dataCriacao, int tipo, double saldo, boolean ativa) {
        super(id, cliente, dataCriacao, saldo, 1, ativa);
    }

    public ContaPessoaFisica(int cliente, int tipo, boolean ativa) {
        super(cliente, 1, ativa);
    }


    @JsonCreator
    public ContaPessoaFisica(
        @JsonProperty("cliente") int cliente,
        @JsonProperty("ativa") boolean ativa
    ) {
        super(cliente, 1, ativa);
    }

    @Override
    public boolean transferencia(double valor, int idReceptor) {
        return true;
    }

    @Override
    public boolean pagar(Cobranca cobranca) {
        return true;
    }

}
