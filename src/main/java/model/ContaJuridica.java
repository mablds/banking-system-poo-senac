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
public class ContaJuridica extends Conta implements Operacoes {

    public ContaJuridica(int id, int cliente, String dataCriacao, int tipo, double saldo, boolean ativa) {
        super(id, cliente, dataCriacao, saldo, 2, ativa);
    }

    public ContaJuridica(int cliente, int tipo, boolean ativa) {
        super(cliente, 2, ativa);
    }

    @JsonCreator
    public ContaJuridica(
        @JsonProperty("cliente") int cliente,
        @JsonProperty("ativa") boolean ativa
    ) {
        super(cliente, 2, ativa);
    }

    @Override
    public boolean transferencia(double valor, int idReceptor) {
        return true;
    }

    @Override
    public boolean pagar(Cobranca cobranca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
