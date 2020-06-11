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
public class Deposito extends Transacao{
    private int idConta;

    public Deposito(int id, Date date, double valor) {
        super(id, date, valor);
    }

    public Deposito(Date date, double valor) {
        super(date, valor);
    }
    
    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
