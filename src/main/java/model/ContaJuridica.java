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
public class ContaJuridica extends Conta implements Operacoes {

    public ContaJuridica(int id, Cliente cliente, Date dataCriacao, double saldo, boolean ativa) {
        super(id, cliente, dataCriacao, saldo, ativa);
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
