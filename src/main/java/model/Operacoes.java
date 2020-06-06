/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sakemi
 */
public interface Operacoes {

    public boolean transferencia(double valor, int idReceptor);

    public boolean pagar(Cobranca cobranca);
}
