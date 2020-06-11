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
public class Saque extends Transacao{
    private int idConta;

    public Saque(int idConta, int id, Date date, double valor) {
        super(id, date, valor);
        this.idConta = idConta;
    }

    public Saque(int idConta, Date date, double valor) {
        super(date, valor);
        this.idConta = idConta;
    }
    
    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
