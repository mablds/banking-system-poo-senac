/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Marcelo
 */
public class ContaPessoaFisica extends Conta implements Operacoes {

    public ContaPessoaFisica(int id, Cliente cliente, Date dataCriacao, double saldo, boolean ativa) {
        super(id, cliente, dataCriacao, saldo, ativa);
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
